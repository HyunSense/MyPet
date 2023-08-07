package com.shopping.mypet.controller.accounts;

import com.shopping.mypet.dto.LoginForm;
import com.shopping.mypet.dto.SignUpForm;
import com.shopping.mypet.entity.Member;
import com.shopping.mypet.repository.MemberRepository;
import com.shopping.mypet.service.MemberServiceV2;
import com.shopping.mypet.util.RegExpConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.shopping.mypet.util.RegExpConst.*;

@Slf4j
@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class MemberControllerV2 {

    private final MemberServiceV2 memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginGet(Model model) {

        model.addAttribute(new LoginForm());

        return "/view/accounts/v2/login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute LoginForm loginForm, BindingResult bindingResult) {

        log.info("loginId = {} password = {}", loginForm.getLoginId(), loginForm.getPassword());


        if (!StringUtils.hasText(loginForm.getLoginId())) {

            bindingResult.addError(new FieldError("loginForm", "loginId", "아이디를 입력해주세요."));
        }

        if (!StringUtils.hasText(loginForm.getPassword())) {

            bindingResult.addError(new FieldError("loginForm", "password", "비밀번호를 입력해주세요."));
        }

        Member login = memberService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (login == null) {

            bindingResult.addError(new ObjectError("globalError", "아이디 또는 비밀번호가 맞지 않습니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "view/accounts/v2/login";
        }


        return "redirect:/";

    }

    @GetMapping("/signup")
    public String signUpGet(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());

        return "view/accounts/v2/signup";
    }

    @PostMapping("/signup")
    public String signUpPostV1(@ModelAttribute SignUpForm signUpForm, BindingResult bindingResult) {

        Pattern passwordPattern = Pattern.compile(REGEXP_USER_PW_TYPE1);
        Matcher passMatcher = passwordPattern.matcher(signUpForm.getPassword());

        Pattern loginIdPattern = Pattern.compile(REGEXP_USER_ID_TYPE2);
        Matcher loginMatcher = loginIdPattern.matcher(signUpForm.getLoginId());

        Pattern emailPattern = Pattern.compile(REGEXP_USER_EMAIL);
        Matcher emailMatcher = emailPattern.matcher(signUpForm.getEmail());

        boolean isDuplicateId = memberService.checkForDuplicateId(signUpForm.getLoginId());

        if (isDuplicateId) {
            bindingResult.addError(new FieldError("signUpForm", "loginId", "이미 존재하는 아이디입니다."));
        }


        if (!StringUtils.hasText(signUpForm.getLoginId())) {
            bindingResult.addError(new FieldError("signUpForm", "loginId", "아이디는 필수입니다."));
        } else if (!loginMatcher.matches()) {
            bindingResult.addError(new FieldError("signUpForm", "loginId", "잘못된 아이디 입니다."));

        }

        if (!passMatcher.matches()) {
            bindingResult.addError(new FieldError("signUpForm", "password", "대소문자 + 숫자 + 특수문자 조합으로 8 ~ 20자리를 입력해주세요."));

        }

        if (!signUpForm.getPassword().equals(signUpForm.getRepeatPassword())) {
            bindingResult.addError(new FieldError("signUpForm", "repeatPassword", "비밀번호가 일치하지 않습니다."));

        }

        if (!signUpForm.getLoginId().isEmpty() && !signUpForm.getPassword().isEmpty() && signUpForm.getLoginId().equals(signUpForm.getPassword())) {
            bindingResult.addError(new ObjectError("signUpForm", "아이디와 비밀번호가 동일해서는 안됩니다."));

        }

        if (!StringUtils.hasText(signUpForm.getEmail())) {
            bindingResult.addError(new FieldError("signUpForm", "email", "이메일은 필수입니다."));

        } else if (!emailMatcher.matches()) {
            bindingResult.addError(new FieldError("signUpForm", "email", "잘못된 이메일입니다."));

        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "view/accounts/v2/signup";
        }

        return "redirect:/login";
    }
}
