package lotto_challenge.core.controller;

import lotto_challenge.core.dto.LottoStatisticResponseDto;
import lotto_challenge.core.dto.SaveLottoStatisticDto;
import lotto_challenge.core.service.LottoStatisticService;

import java.util.List;

public class LottoStatisticController {

    private final LottoStatisticService lottoStatisticService;

    public LottoStatisticController(final LottoStatisticService lottoStatisticService) {
        this.lottoStatisticService = lottoStatisticService;
    }

    public void saveLottoStatistic(final Long memberId, final SaveLottoStatisticDto dto) {
        lottoStatisticService.saveLottoStatistic(memberId, dto);
    }

    public List<LottoStatisticResponseDto> getLottoStatistics(final Long memberId) {
        return lottoStatisticService.getLottoStatistics(memberId);
    }
}
