package com.shpping.mypet.repository;

import com.shpping.mypet.entity.Member;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static long autoIncrementId = 0L;

    @Override
    public Member save(Member member) {

        member.setId(++autoIncrementId);
        log.info("save: member={}", member);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {

        return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findAny();
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }
}
