package com.shopping.mypet.controller;


import com.shopping.mypet.entity.Member;
import com.shopping.mypet.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/")
    public String home() {

        return "/view/index";
    }

    // Spring의 @CookieValue를 통해서 편리하게 쿠기 조회가 가능
    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {


        log.info("memberId = {}", memberId);


//        if (memberId == null) {
//            return "view/index";
//        }

        Member member = memberRepository.findById(memberId);

        if (member == null) {
            return "view/index";
        }
        // 로그인
        model.addAttribute("member", member);
        return "view/index";

    }


}
