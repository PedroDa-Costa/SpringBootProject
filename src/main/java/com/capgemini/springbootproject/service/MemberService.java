package com.capgemini.springbootproject.service;

import com.capgemini.springbootproject.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> findAll();

    Member findById(int id);

    int findIdByMember(Member member);

    void save(Member member);
}
