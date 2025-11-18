package lotto_challenge.core.repository.jpa;

import lotto_challenge.core.repository.jpa.entity.LottoStatisticEntity;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaLottoStatisticRepository extends JpaRepository<LottoStatisticEntity, Long> {

    @Query("""
        SELECT new lotto_challenge.core.service.dto.LottoStatisticInfoDto(
            ls.purchasePrice,
            ls.returnRate
        )
        FROM LottoStatisticEntity ls
        JOIN ls.member m
        WHERE m.email = :email
    """)
    List<LottoStatisticInfoDto> findAllByMemberEmail(final String email);
}
