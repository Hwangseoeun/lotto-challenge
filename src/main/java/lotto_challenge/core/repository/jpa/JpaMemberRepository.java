package lotto_challenge.core.repository.jpa;

import lotto_challenge.core.repository.jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(final String email);
    Long findMemberIdByEmail(final String email);
}
