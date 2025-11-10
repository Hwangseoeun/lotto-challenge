package lotto_challenge.controller;

import lotto_challenge.dto.LottoStatisticResponseDto;
import lotto_challenge.dto.SaveLottoStatisticDto;
import lotto_challenge.service.LottoStatisticService;

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
