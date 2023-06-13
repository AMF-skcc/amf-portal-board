package com.factory.demo.member.controller;

import com.factory.demo.member.dto.MemberCreateRequestDto;
import com.factory.demo.member.model.Member;
import com.factory.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Long save(@RequestBody MemberCreateRequestDto requestDto) {
        return this.memberService.save(requestDto);
    }

    @GetMapping("/{id}")
    public Member findMemberById(@PathVariable Long id) {
        return memberService.findMemberById(id);
    }

}
