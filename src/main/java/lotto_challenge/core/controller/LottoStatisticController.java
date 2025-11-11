package lotto_challenge.core.controller;

import lotto_challenge.core.dto.LottoStatisticResponseDto;
import lotto_challenge.core.dto.SaveLottoStatisticDto;

import java.util.List;

public interface LottoStatisticController {

    void saveLottoStatistic(final Long memberId, final SaveLottoStatisticDto dto);
    List<LottoStatisticResponseDto> getLottoStatistics(final Long memberId);
}
