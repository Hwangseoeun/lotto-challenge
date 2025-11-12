package lotto_challenge.core.service.dto;

import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.WinningRankCounter;

public record JudgeRankDto(

    Lottos lottos,
    WinningRankCounter winningRankCounter

) {
}
