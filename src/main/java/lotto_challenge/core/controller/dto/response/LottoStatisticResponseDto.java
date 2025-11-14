package lotto_challenge.core.controller.dto.response;

import lotto_challenge.core.service.dto.LottoStatisticInfoDto;

import java.util.List;

public record LottoStatisticResponseDto(

    List<LottoStatisticInfoDto> lottoStatisticInfos

) {
}
