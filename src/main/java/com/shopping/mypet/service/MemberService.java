package com.shopping.mypet.service;

import com.shopping.mypet.dto.LoginForm;
import com.shopping.mypet.dto.SignUpForm;
import com.shopping.mypet.dto.MemberDto;
import com.shopping.mypet.entity.Member;
import com.shopping.mypet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.shopping.mypet.util.RegExpConst.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        // TODO: Null 일때 처리를 어떻게 할 것인가?
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    public boolean memberIdIsPresent(String loginId) {

        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        // 존재한다면 true 반환
        return findMember.isPresent();
    }

    public Map<String, String> loginMemberValidation(LoginForm loginForm) {

        Map<String, String> errors = new HashMap<>();

        if (!StringUtils.hasText(loginForm.getLoginId())) {

            errors.put("loginId", "아이디를 입력해주세요.");
        }

        if (!StringUtils.hasText(loginForm.getPassword())) {

            errors.put("password", "비밀번호를 입력해주세요.");
        }

        Member login = login(loginForm.getLoginId(), loginForm.getPassword());

        if (login == null) {

            errors.put("globalError", "아이디 또는 비밀번호가 맞지 않습니다.");

        }

        return errors;

    }

    public Map<String, String> signupMemberValidation(SignUpForm createMember) {


        Map<String, String> errors = new HashMap<>();

        Pattern passwordPattern = Pattern.compile(REGEXP_USER_PW_TYPE1);
        Matcher passMatcher = passwordPattern.matcher(createMember.getPassword());

        Pattern loginIdPattern = Pattern.compile(REGEXP_USER_ID2);
        Matcher loginMatcher = loginIdPattern.matcher(createMember.getLoginId());

        Pattern emailPattern = Pattern.compile(REGEXP_USER_EMAIL);
        Matcher emailMatcher = emailPattern.matcher(createMember.getEmail());

        //검증 로직

        boolean findMember = memberIdIsPresent(createMember.getLoginId());

        if (findMember) {

            errors.put("loginId", "이미 존재하는 아이디입니다.");
        }

        if (!StringUtils.hasText(createMember.getLoginId())) {

            errors.put("loginId", "아이디는 필수입니다.");
        }

        if (!loginMatcher.matches()) {

            errors.put("loginId", "잘못된 아이디 입니다.");
        }

        if (!passMatcher.matches()) {

            errors.put("password", "대소문자 + 숫자 + 특수문자 조합으로 8 ~ 20자리를 입력해주세요.");
        }

        if (!createMember.getPassword().equals(createMember.getRepeatPassword())) {

            errors.put("repeatPassword", "비밀번호가 일치하지 않습니다.");
        }

        if (createMember.getEmail().equals(createMember.getPassword())) {

            errors.put("password", "아이디와 비밀번호가 동일해서는 안됩니다.");
        }

        if (!StringUtils.hasText(createMember.getEmail())) {

            errors.put("email", "이메일은 필수입니다.");
        }

        if (!emailMatcher.matches()) {

            errors.put("email", "잘못된 이메일입니다.");
        }

        return errors;
    }
}
