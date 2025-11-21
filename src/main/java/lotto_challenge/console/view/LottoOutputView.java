package lotto_challenge.console.view;

import lotto_challenge.core.controller.dto.RankResultDto;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;

import java.util.List;

public class LottoOutputView {

    private static final int SECOND_PRIZE_MATCHED_NUMBER_COUNT = 5;
    private static final int SECOND_PRIZE_MONEY = 30000000;

    public void outputExceptionMessage(final IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public void outputServerExceptionMessage(final Exception e) {
        System.out.println(LottoOutputGuideMessage.OUTPUT_SERVER_EXCEPTION_GUIDE_MESSAGE.getMessage());
        System.out.println();
    }

    public void outputLottos(final int lottoQuantity, final List<List<Integer>> lottos) {
        System.out.println(lottoQuantity + LottoOutputGuideMessage.OUTPUT_LOTTO_QUANTITY_GUIDE_MESSAGE.getMessage());

        for(List<Integer> lotto : lottos) {
            System.out.println(lotto);
        }

        System.out.println();
    }

    public void outputWinningResult(final List<RankResultDto> winningRanks) {
        System.out.println(LottoOutputGuideMessage.OUTPUT_WINNING_RESULT_GUIDE_MESSAGE.getMessage());

        for (RankResultDto dto : winningRanks) {
            if (dto.matchedNumberCount() == SECOND_PRIZE_MATCHED_NUMBER_COUNT && dto.prizeMoney() == SECOND_PRIZE_MONEY) {
                System.out.printf(
                    LottoOutputGuideMessage.OUTPUT_WINNING_RESULT_SECOND_PRIZE_GUIDE_MESSAGE.getMessage(),
                    dto.matchedNumberCount(),
                    dto.prizeMoney(),
                    dto.count()
                );
                System.out.println();
                continue;
            }

            System.out.printf(
                LottoOutputGuideMessage.OUTPUT_WINNING_RESULT_OTHER_PRIZE_GUIDE_MESSAGE.getMessage(),
                dto.matchedNumberCount(),
                dto.prizeMoney(),
                dto.count()
            );
            System.out.println();
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
