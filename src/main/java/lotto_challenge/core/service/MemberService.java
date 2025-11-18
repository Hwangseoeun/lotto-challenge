package lotto_challenge.core.service;

import lotto_challenge.core.model.Member;
import lotto_challenge.core.repository.MemberRepository;
import lotto_challenge.core.service.dto.SaveEmailDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long save(final SaveEmailDto dto) {
        final Member member = new Member(dto.email());

        if(memberRepository.existsByEmail(member.getEmail())) {
            return memberRepository.findIdByEmail(member.getEmail());
        }

        return memberRepository.save(member);
    }
}
