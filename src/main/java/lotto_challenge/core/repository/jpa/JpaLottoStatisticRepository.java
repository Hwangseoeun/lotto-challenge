package lotto_challenge.core.repository.jpa;

import lotto_challenge.core.repository.jpa.entity.LottoStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLottoStatisticRepository extends JpaRepository<LottoStatisticEntity, Long> {

    List<LottoStatisticEntity> findAllByMemberEmail(final String email);
}
