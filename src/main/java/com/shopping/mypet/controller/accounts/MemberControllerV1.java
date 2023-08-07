package com.shopping.mypet.controller.accounts;

import com.shopping.mypet.dto.LoginForm;
import com.shopping.mypet.dto.SignUpForm;
import com.shopping.mypet.repository.MemberRepository;
import com.shopping.mypet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class MemberControllerV1 {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginGet(Model model) {

        model.addAttribute(new LoginForm());

        return "/view/accounts/login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute LoginForm loginForm, Model model) {



        Map<String, String> errors = memberService.loginMemberValidation(loginForm);
        log.info("loginId = {} password = {}", loginForm.getLoginId(), loginForm.getPassword());

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
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
    public String signUpPostV1(@ModelAttribute SignUpForm signUpForm, Model model) {

        //검증 오류 결과를 보관
        Map<String, String> errors = memberService.signupMemberValidation(signUpForm);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "view/accounts/signup";
        }



        /**
         * 회원가입 폼 작성 -> 필수값 검증 -> DB 조회(중복 아이디 찾기)
         * 있을경우 -> Member 생성? (중복확인 버튼을 누르는것이 더 효과적?)
         * 없을경우 -> 예외 발생
         */
        // MemoryMemberRepository를 사용하고있음으로 임시적으로 DAO 생략?

//        Member member = Member.builder()
//                .loginId(memberCreateForm.getEmail())
//                .password(memberCreateForm.getPassword())
//                .build();
//
//        memberRepository.save(member);

        return "redirect:/";
    }
}
