package lotto_challenge.core.service;

import lotto_challenge.core.dto.LottoStatisticResponseDto;
import lotto_challenge.core.dto.SaveLottoStatisticDto;
import lotto_challenge.core.repository.LottoStatisticRepository;

import java.util.List;

public class LottoStatisticService {

    private final LottoStatisticRepository lottoStatisticRepository;

    public LottoStatisticService(final LottoStatisticRepository lottoStatisticRepository) {
        this.lottoStatisticRepository = lottoStatisticRepository;
    }

    public void saveLottoStatistic(final Long memberId, final SaveLottoStatisticDto dto) {
        lottoStatisticRepository.save(memberId, dto.getPurchasePrice(), dto.getReturnRate());
    }

    public List<LottoStatisticResponseDto> getLottoStatistics(final Long memberId) {
        return lottoStatisticRepository.findByMemberId(memberId);
    }
}
