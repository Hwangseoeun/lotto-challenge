package lotto_challenge.core.service;

import lotto_challenge.core.model.Member;
import lotto_challenge.core.repository.MemberRepository;
import lotto_challenge.core.service.dto.GetMemberDto;
import lotto_challenge.core.service.dto.SaveEmailDto;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long save(final SaveEmailDto dto) {
        final Member member = new Member(dto.email());

        if(memberRepository.existsByEmail(member.getEmail())) {
            return memberRepository.findMemberIdByEmail(member.getEmail());
        }

        return memberRepository.save(member);
    }

    public Long getMember(final GetMemberDto dto) {
        final Member member = new Member(dto.email());
        final Long memberId = memberRepository.findMemberIdByEmail(member.getEmail());
        validateMember(memberId);

        return memberId;
    }

    private void validateMember(final Long memberId) {
        if(memberId == null) {
            throw new IllegalArgumentException();
        }
    }
}
