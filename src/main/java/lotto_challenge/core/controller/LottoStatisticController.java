package lotto_challenge.core.controller;

import lotto_challenge.dto.LottoStatisticResponseDto;
import lotto_challenge.dto.SaveLottoStatisticDto;

import java.util.List;

public interface LottoStatisticController {

    void saveLottoStatistic(final Long memberId, final SaveLottoStatisticDto dto);
    List<LottoStatisticResponseDto> getLottoStatistics(final Long memberId);
}
