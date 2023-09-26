package com.interview.preparation.low_level_design.stack_overflow.service;

import com.interview.preparation.low_level_design.stack_overflow.exception.MemberNotFoundException;
import com.interview.preparation.low_level_design.stack_overflow.model.Member;
import com.interview.preparation.low_level_design.stack_overflow.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member){
        return memberRepository.addMember(member);
    }

    public Member getMemberById(String memberId) throws MemberNotFoundException {
        return memberRepository.getMemberById(memberId);
    }
}
