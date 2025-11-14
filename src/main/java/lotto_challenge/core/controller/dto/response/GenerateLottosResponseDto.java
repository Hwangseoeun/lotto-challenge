package lotto_challenge.core.controller.dto.response;

import lotto_challenge.core.controller.dto.RankResultDto;
import lotto_challenge.core.model.Lotto;
import lotto_challenge.core.model.Rank;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;
import lotto_challenge.core.service.dto.LottosDetailDto;

import java.util.List;

public record GenerateLottosResponseDto(

    int lottoQuantity,
    List<List<Integer>> lottos,
    List<RankResultDto> winningRanks,
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

        final List<RankResultDto> rankDtos = counter.getResult().entrySet().stream()
            .filter(entry -> entry.getKey() != Rank.NONE)
            .map(entry -> {
                Rank rank = entry.getKey();
                int count = entry.getValue();
                return new RankResultDto(
                    rank.getMatchedNumberCount(),
                    rank.getPrizeMoney(),
                    count
                );
            })
            .toList();

        return new GenerateLottosResponseDto(
            detail.lottoQuantity().getValue(),
            lottoNumbers,
            rankDtos,
            rate.getValue()
        );
    }
}
