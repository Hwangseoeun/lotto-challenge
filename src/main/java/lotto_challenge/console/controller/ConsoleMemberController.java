package lotto_challenge.console.controller;

import lotto_challenge.core.controller.MemberController;
import lotto_challenge.model.Member;
import lotto_challenge.service.MemberService;

public class ConsoleMemberController implements MemberController {

    private final MemberService memberService;

    public ConsoleMemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Long saveMember(final String email) {
        final Member member = new Member(email);

        if(memberService.existsByEmail(email)) {
            return memberService.getMember(member);
        }

        return memberService.saveMember(member);
    }

    @Override
    public Long getMember(final String email) {
        final Member member = new Member(email);

        return memberService.getMember(member);
    }
}
