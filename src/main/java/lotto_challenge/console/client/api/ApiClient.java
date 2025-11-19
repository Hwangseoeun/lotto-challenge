package lotto_challenge.console.client.api;

import lotto_challenge.core.controller.dto.request.GenerateLottosRequestDto;
import lotto_challenge.core.controller.dto.request.LottoStatisticRequestDto;
import lotto_challenge.core.controller.dto.response.GenerateLottosResponseDto;
import lotto_challenge.core.controller.dto.response.LottoStatisticResponseDto;

public interface ApiClient {

    GenerateLottosResponseDto generateLottosApi(final GenerateLottosRequestDto request) throws Exception;
    LottoStatisticResponseDto getLottoStatisticsApi(final LottoStatisticRequestDto request) throws Exception;

}
