package lotto_challenge.core.repository;

import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;

import java.util.List;

public interface LottoStatisticRepository {

    void save(final Long memberId, final PurchasePrice purchasePrice, final ReturnRate returnRate);

    List<LottoStatisticInfoDto> findAllByMemberEmail(final String email);
}
