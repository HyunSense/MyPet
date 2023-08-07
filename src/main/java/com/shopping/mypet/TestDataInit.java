package com.shopping.mypet;

import com.shopping.mypet.entity.Member;
import com.shopping.mypet.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {


    private final MemberRepository memberRepository;

    /**
     * 테스트용 멤버 추가
     */

    @PostConstruct
    public void init() {
        Member member1 = Member.builder()
                .loginId("1234@google.com")
                .password("1234")
                .build();

        Member member2 = Member.builder()
                .loginId("hello@google.com")
                .password("1234")
                .build();

        Member member3 = Member.builder()
                .loginId("123gtgt")
                .password("1234")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }
}
