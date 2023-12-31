package com.shopping.mypet.service;

import com.shopping.mypet.entity.Member;
import com.shopping.mypet.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean checkForDuplicateId(String loginId) {

        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        // 존재한다면 true 반환
        return findMember.isPresent();
    }
}
