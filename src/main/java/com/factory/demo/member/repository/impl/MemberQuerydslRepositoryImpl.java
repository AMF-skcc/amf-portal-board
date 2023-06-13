package com.factory.demo.member.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.factory.demo.member.model.Member;
import com.factory.demo.member.model.QMember;
import com.factory.demo.member.repository.MemberQuerydslRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberQuerydslRepositoryImpl implements MemberQuerydslRepository{

    @Autowired
    private JPAQueryFactory factory;

    @Override
    public Member findMemberById(Long id) {
      QMember member = QMember.member;
      return factory.selectFrom(member)
              .where(member.id.eq(id))
              .fetchOne();
    }

}
