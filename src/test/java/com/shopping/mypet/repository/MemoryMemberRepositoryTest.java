package com.shopping.mypet.repository;

import com.shopping.mypet.entity.Member;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemoryMemberRepositoryTest {

    MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void save() {

        Member member = Member.builder()
                .loginId("test1@google.com")
                .password("1234")
                .build();


        Member savedMember = memberRepository.save(member);

        assertThat(savedMember.getLoginId()).isEqualTo("test1@google.com");
        assertThat(savedMember.getLoginId()).isEqualTo(member.getLoginId());
    }

    @Test
    void findById() {

        Member member = Member.builder()
                .loginId("test1@google.com")
                .password("1234")
                .build();

        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());

        assertThat(findMember.getLoginId()).isEqualTo(savedMember.getLoginId());
        assertThat(findMember.getLoginId()).isEqualTo("test1@google.com");
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    void findByLoginId() {

        Member member1 = Member.builder()
                .loginId("test1@google.com")
                .password("1234")
                .build();

        Member member2 = Member.builder()
                .loginId("test2@naver.com")
                .password("0000")
                .build();

        Member member3 = Member.builder()
                .loginId("test3@daum.net")
                .password("4321")
                .build();

        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);
        Member savedMember3 = memberRepository.save(member3);

        Optional<Member> loginId = memberRepository.findByLoginId(savedMember2.getLoginId());

        assertThat(loginId.get().getLoginId()).isEqualTo(member2.getLoginId());
        assertThat(loginId.get().getLoginId()).isEqualTo("test2@naver.com");
    }

    @Test
    void findAll() {

        Member member1 = Member.builder()
                .loginId("test1@google.com")
                .password("1234")
                .build();

        Member member2 = Member.builder()
                .loginId("test2@naver.com")
                .password("0000")
                .build();

        Member member3 = Member.builder()
                .loginId("test3@daum.net")
                .password("4321")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList.get(0)).isEqualTo(member1);
        assertThat(memberList.get(1).getId()).isEqualTo(2L);
        assertThatThrownBy(() -> memberList.get(3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
}