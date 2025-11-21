package lotto_challenge.core.repository;

import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.repository.jpa.JpaLottoStatisticRepository;
import lotto_challenge.core.repository.jpa.JpaMemberRepository;
import lotto_challenge.core.repository.jpa.entity.LottoStatisticEntity;
import lotto_challenge.core.repository.jpa.entity.MemberEntity;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LottoStatisticRepositoryImpl implements LottoStatisticRepository {

    private final JpaLottoStatisticRepository jpaLottoStatisticRepository;
    private final JpaMemberRepository jpaMemberRepository;

    public LottoStatisticRepositoryImpl(final JpaLottoStatisticRepository jpaLottoStatisticRepository, final JpaMemberRepository jpaMemberRepository) {
        this.jpaLottoStatisticRepository = jpaLottoStatisticRepository;
        this.jpaMemberRepository = jpaMemberRepository;
    }

    @Override
    public void save(
        final Long memberId,
        final PurchasePrice purchasePrice,
        final ReturnRate returnRate
    ) {
        final MemberEntity memberEntity = jpaMemberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member Id: " + memberId + " not found"));

        final LottoStatisticEntity lottoStatisticEntity = new LottoStatisticEntity(
            memberEntity,
            purchasePrice,
            returnRate
        );
        jpaLottoStatisticRepository.save(lottoStatisticEntity);
    }

    @Override
    public List<LottoStatisticInfoDto> findAllByMemberEmail(final String email) {
        return jpaLottoStatisticRepository.findAllByMemberEmail(email);
    }
}
