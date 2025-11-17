package lotto_challenge.core.repository;

import lotto_challenge.core.model.Member;

public interface MemberRepository {

    Long save(final Member member);

    boolean existsByEmail(final String email);

    Long findMemberIdByEmail(final String email);
}
