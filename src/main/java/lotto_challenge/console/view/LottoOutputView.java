package lotto_challenge.console.view;

import lotto_challenge.core.model.Rank;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;

import java.util.List;
import java.util.Map;

public class LottoOutputView {

    public void outputExceptionMessage(final IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public void outputLottos(final int lottoQuantity, final List<List<Integer>> lottos) {
        System.out.println(lottoQuantity + LottoOutputGuideMessage.OUTPUT_LOTTO_QUANTITY_GUIDE_MESSAGE.getMessage());

        for(List<Integer> lotto : lottos) {
            System.out.println(lotto);
        }

        System.out.println();
    }

    public void outputWinningResult(final Map<Rank, Integer> winningRankCounter) {
        System.out.println(LottoOutputGuideMessage.OUTPUT_WINNING_RESULT_GUIDE_MESSAGE.getMessage());

        for(Map.Entry<Rank, Integer> entry : winningRankCounter.entrySet()) {
            final Rank rank = entry.getKey();
            final Integer count = entry.getValue();

            if(rank == Rank.NONE) {
                continue;
            }

            if(rank == Rank.SECOND_PRIZE) {
                System.out.println(String.format(LottoOutputGuideMessage.OUTPUT_WINNING_RESULT_SECOND_PRIZE_GUIDE_MESSAGE.getMessage(), rank.getMatchedNumberCount(), rank.getPrizeMoney(), count));
                continue;
            }

            System.out.println(String.format(LottoOutputGuideMessage.OUTPUT_WINNING_RESULT_OTHER_PRIZE_GUIDE_MESSAGE.getMessage(), rank.getMatchedNumberCount(), rank.getPrizeMoney(), count));
        }
    }

    public void outputReturnRate(final float returnRate) {
        System.out.println(String.format(LottoOutputGuideMessage.OUTPUT_RETURN_RATE_GUIDE_MESSAGE.getMessage(), returnRate, '%'));
        System.out.println();
    }

    public void outputLottoStatistics(final List<LottoStatisticInfoDto> lottoStatistics) {
        System.out.println(LottoOutputGuideMessage.OUTPUT_LOTTO_STATISTIC_GUIDE_MESSAGE.getMessage());
        System.out.println(LottoOutputGuideMessage.OUTPUT_SEPARATOR.getMessage());

        for(LottoStatisticInfoDto dto : lottoStatistics) {
            System.out.println(String.format("구매 금액 : %d원 | 수익률 : %f%c", dto.getPurchasePrice(), dto.getReturnRate(), '%'));
        }

        System.out.println();
    }

    public void outputInvalidMember() {
        System.out.println(LottoOutputGuideMessage.OUTPUT_INVALID_MEMBER_GUIDE_MESSAGE.getMessage());
        System.out.println();
    }
}
