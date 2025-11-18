package lotto_challenge.core.repository.jpa;

import lotto_challenge.core.repository.jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(final String email);

    @Query(
        "SELECT m.id " +
        "FROM MemberEntity m " +
        "WHERE m.email = :email"
    )
    Long findIdByEmail(final String email);
}
