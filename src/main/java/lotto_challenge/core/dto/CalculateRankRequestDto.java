package lotto_challenge.core.dto;

import lotto_challenge.core.model.Lottos;

import java.util.List;

public record CalculateRankRequestDto(

    Lottos lottos,
    List<Integer> winningLottoNumbers,
    String bonusNumber

) {
}
