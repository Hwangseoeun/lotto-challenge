package lotto_challenge.core.controller.dto.response;

import lotto_challenge.core.model.Lotto;
import lotto_challenge.core.model.Rank;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;
import lotto_challenge.core.service.dto.LottosDetailDto;

import java.util.List;
import java.util.Map;

public record GenerateLottosResponseDto(

    int lottoQuantity,
    List<List<Integer>> lottos,
    Map<Rank, Integer> winningRankCounter,
    float returnRate

) {
    public static GenerateLottosResponseDto from(
        final LottosDetailDto detail,
        final WinningRankCounter counter,
        final ReturnRate rate
    ) {
        final List<List<Integer>> lottoNumbers = detail.lottos().getValue().stream()
            .map(Lotto::getNumbers)
            .toList();

        return new GenerateLottosResponseDto(
            detail.lottoQuantity().getValue(),
            lottoNumbers,
            counter.getResult(),
            rate.getValue()
        );
    }
}
