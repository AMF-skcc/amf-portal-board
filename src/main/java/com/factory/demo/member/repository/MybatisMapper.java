package com.factory.demo.member.repository;


import org.apache.ibatis.annotations.Mapper;

import com.factory.demo.member.model.Member;

@Mapper
public interface MybatisMapper {
    Member findMemberById(Long id);
}
