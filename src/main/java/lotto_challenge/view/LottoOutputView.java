package lotto_challenge.view;

import lotto_challenge.model.Lotto;
import lotto_challenge.model.LottoQuantity;
import lotto_challenge.model.Lottos;
import lotto_challenge.model.Rank;
import lotto_challenge.model.ReturnRate;
import lotto_challenge.model.WinningRankCounter;

import java.util.Map;

public class LottoOutputView {

    public void outputExceptionMessage(final IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public void outputLottos(final LottoQuantity lottoQuantity, final Lottos lottos) {
        System.out.println(lottoQuantity.getValue() + LottoOutputGuideMessage.OUTPUT_LOTTO_QUANTITY_GUIDE_MESSAGE.getMessage());

        for(Lotto lotto : lottos.getValue()) {
            System.out.println(lotto.getNumbers());
        }

        System.out.println();
    }

    public void outputWinningResult(final WinningRankCounter winningRankCounter) {
        System.out.println(LottoOutputGuideMessage.OUTPUT_WINNING_RESULT_GUIDE_MESSAGE.getMessage());
        final Map<Rank, Integer> result = winningRankCounter.getResult();

        for(Map.Entry<Rank, Integer> entry : result.entrySet()) {
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

    public void outputReturnRate(final ReturnRate returnRate) {
        System.out.println(String.format(LottoOutputGuideMessage.OUTPUT_RETURN_RATE_GUIDE_MESSAGE.getMessage(), returnRate.getValue(), '%'));
        System.out.println();
    }
}
