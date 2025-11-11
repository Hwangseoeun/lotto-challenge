package lotto_challenge.console.controller;

import lotto_challenge.core.controller.LottoStatisticController;
import lotto_challenge.core.dto.LottoStatisticResponseDto;
import lotto_challenge.core.dto.SaveLottoStatisticDto;
import lotto_challenge.core.service.LottoStatisticService;

import java.util.List;

public class ConsoleLottoStatisticController implements LottoStatisticController {

    private final LottoStatisticService lottoStatisticService;

    public ConsoleLottoStatisticController(final LottoStatisticService lottoStatisticService) {
        this.lottoStatisticService = lottoStatisticService;
    }

    @Override
    public void saveLottoStatistic(final Long memberId, final SaveLottoStatisticDto dto) {
        lottoStatisticService.saveLottoStatistic(memberId, dto);
    }

    @Override
    public List<LottoStatisticResponseDto> getLottoStatistics(final Long memberId) {
        return lottoStatisticService.getLottoStatistics(memberId);
    }
}
