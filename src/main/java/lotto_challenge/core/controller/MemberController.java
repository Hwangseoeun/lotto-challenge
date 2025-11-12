package lotto_challenge.core.controller;

import lotto_challenge.core.model.Member;
import lotto_challenge.core.service.MemberService;

public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    public Long saveMember(final String email) {
        final Member member = new Member(email);

        if(memberService.existsByEmail(email)) {
            return memberService.getMember(member);
        }

        return memberService.saveMember(member);
    }

    public Long getMember(final String email) {
        final Member member = new Member(email);

        return memberService.getMember(member);
    }
}
