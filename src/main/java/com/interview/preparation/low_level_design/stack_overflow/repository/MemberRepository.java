package com.interview.preparation.low_level_design.stack_overflow.repository;

import com.interview.preparation.low_level_design.stack_overflow.exception.MemberNotFoundException;
import com.interview.preparation.low_level_design.stack_overflow.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    Map<String , Member> memberMap  = new HashMap<>();
    List<String> memberList = new ArrayList<>();

    public Member addMember(Member member){
        memberMap.putIfAbsent(member.getId() , member);
        memberList.add(member.getId());
        return member;
    }

    public Member getMemberById(String memberId) throws MemberNotFoundException {
        Member member = memberMap.get(memberId);
        if(member==null){
            throw new MemberNotFoundException("provided memberId is not valid");
        }
        return member;
    }
}
