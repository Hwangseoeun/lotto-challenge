package lotto_challenge.service;

import lotto_challenge.dto.LottoStatisticResponseDto;
import lotto_challenge.dto.SaveLottoStatisticDto;
import lotto_challenge.repository.LottoStatisticRepository;

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
