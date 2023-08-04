package com.capgemini.springbootproject.service;

import com.capgemini.springbootproject.dao.MemberRepository;
import com.capgemini.springbootproject.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private EntityManager entityManager;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, EntityManager entityManager) {
        this.memberRepository = memberRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(int id) {
        Optional<Member> result = memberRepository.findById(id);
        Member member;
        if(result.isPresent()) {
            member = result.get();
        } else {
            throw new RuntimeException("Did not find member id: " + id);
        }

        return member;
    }

    @Override
    public int findIdByMember(Member member) {
        TypedQuery<Member> query = entityManager.createQuery("FROM Member WHERE username = :user", Member.class);
        query.setParameter("user", member.getUsername());
        List<Member> memberList = query.getResultList();
        if(memberList.isEmpty()) {
            return -1;
        }
        return memberList.get(0).getId();

    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

}
