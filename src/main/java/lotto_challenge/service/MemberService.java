package lotto_challenge.service;

import lotto_challenge.model.Member;
import lotto_challenge.repository.MemberRepository;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean existsByEmail(final String email) {
        return memberRepository.existsByEmail(email);
    }

    public Long getMember(final Member member) {
        return memberRepository.findMemberIdByEmail(member.getEmail());
    }

    public Long saveMember(final Member member) {
        return memberRepository.save(member);
    }
}
