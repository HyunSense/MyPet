package com.shopping.mypet.controller.accounts;

import com.shopping.mypet.dto.LoginForm;
import com.shopping.mypet.dto.SignUpForm;
import com.shopping.mypet.validation.ValidationSequence;
import com.shopping.mypet.entity.Member;
import com.shopping.mypet.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public String loginPost(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {

        Member loginMember = memberService.login(loginForm.getLoginId(), loginForm.getPassword());

        log.info("loginForm.getLoginId() = {}, loginForm.getPassword() = {}", loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("required.global");
            log.info("errors={}", bindingResult);
            return "view/accounts/login";
        }

        // 로그인 성공시 Home
        Cookie loginCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        loginCookie.setPath("/");
        response.addCookie(loginCookie);
        log.info("loginIdCookie.getName = {}, loginIdCookie.getValue = {}"
                , loginCookie.getName(), loginCookie.getValue());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("memberId", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);


        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signUpGet(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());

        return "view/accounts/signup";
    }

    // devU01TX0FVVEgyMDIzMDkwNDE5MzY0NDExNDA3NTA=
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
