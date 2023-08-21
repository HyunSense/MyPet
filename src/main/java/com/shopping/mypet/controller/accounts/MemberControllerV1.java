package com.shopping.mypet.controller.accounts;

import com.shopping.mypet.dto.LoginForm;
import com.shopping.mypet.dto.SignUpForm;
import com.shopping.mypet.dto.ValidationSequence;
import com.shopping.mypet.entity.Member;
import com.shopping.mypet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class MemberControllerV1 {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute(new LoginForm());

        return "/view/accounts/login";
    }


    @PostMapping("/login")
    public String loginPost(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult) {

        Member login = memberService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (login == null) {
            bindingResult.reject("required.global");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "view/accounts/login";
        }

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signUpGet(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());

        return "view/accounts/signup";
    }


    @PostMapping("/signup")
    public String signUpPost(@Validated(ValidationSequence.class) @ModelAttribute SignUpForm signUpForm, BindingResult bindingResult) {

         boolean isDuplicateId = memberService.checkForDuplicateId(signUpForm.getLoginId());

         if (isDuplicateId) {
             bindingResult.rejectValue("loginId", "signUpForm.duplicate.loginId");
         }

        if (!signUpForm.getLoginId().isEmpty() && !signUpForm.getPassword().isEmpty() && signUpForm.getLoginId().equals(signUpForm.getPassword())) {
            bindingResult.reject("signUpForm.equal");
        }

        if (!signUpForm.getPassword().equals(signUpForm.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword", "signUpForm.notMatch.repeatPassword");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "view/accounts/signup";
        }
        return "redirect:/accounts/login";
    }

}
