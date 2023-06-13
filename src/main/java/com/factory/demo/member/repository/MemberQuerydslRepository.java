package com.factory.demo.member.repository;

import com.factory.demo.member.model.Member;

public interface MemberQuerydslRepository {

  Member findMemberById(Long id);

}
