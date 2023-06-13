package com.factory.demo.member.service.impl;

import com.factory.demo.member.dto.MemberCreateRequestDto;
import com.factory.demo.member.dto.MemberLoginRequestDto;
import com.factory.demo.member.dto.LoginResultDto;
import com.factory.demo.member.dto.LoginSuccessResultDto;
import com.factory.demo.member.model.Member;
import com.factory.demo.member.repository.MemberRepository;
import com.factory.demo.member.repository.MybatisMapper;
import com.factory.demo.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MybatisMapper mybatisRepository;

    @Override
    @Transactional
    public Long save(MemberCreateRequestDto requestDto) {
        // 이미 등록된 Email이면 -1을 return하는 것이 API 스펙이라고 가정함
        Member savedMember = memberRepository.findByEmail(requestDto.getEmail()); // 혹은 Exists 함수를 사용할 수 있음
        if (savedMember != null) {
            return -1L;
        }

        return this.memberRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public LoginResultDto login(MemberLoginRequestDto requestDto) {
        Member savedMember = this.memberRepository.findByEmail(requestDto.getEmail());
        if (savedMember == null) {
            return new LoginResultDto(false); // 요구사항에 따라 다른 방법으로 구현할 수 있음
        }
        if (!savedMember.getPassword().equals(requestDto.getPassword())) {
            return new LoginResultDto(false); // 요구사항에 따라 다른 방법으로 구현할 수 있음
        }
        return new LoginSuccessResultDto(savedMember.getId(), savedMember.getName(), savedMember.getEmail());
    }

    @Override
    public Member findMemberById(Long id) {
        return mybatisRepository.findMemberById(id);
    }
}
