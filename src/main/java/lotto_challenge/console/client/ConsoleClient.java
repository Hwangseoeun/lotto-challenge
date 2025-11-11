package lotto_challenge.console.client;

import lotto_challenge.console.view.LottoOutputView;
import lotto_challenge.core.controller.LottoController;
import lotto_challenge.core.controller.LottoStatisticController;
import lotto_challenge.core.controller.MemberController;
import lotto_challenge.core.dto.CalculateRankRequestDto;
import lotto_challenge.core.dto.LottoStatisticResponseDto;
import lotto_challenge.core.dto.LottosInfoResponseDto;
import lotto_challenge.core.dto.PurchaseRequestDto;
import lotto_challenge.core.dto.SaveLottoStatisticDto;
import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;

import java.util.List;

public class ConsoleClient {

    private static final int FIRST_OPTION = 1;
    private static final int SECOND_OPTION = 2;
    private static final int THIRD_OPTION = 3;

    private final InputHandler inputHandler;
    private final LottoOutputView lottoOutputView;
    private final MemberController memberController;
    private final LottoController lottoController;
    private final LottoStatisticController lottoStatisticController;

    public ConsoleClient(
        final InputHandler inputHandler,
        final LottoOutputView lottoOutputView,
        final MemberController memberController,
        final LottoController lottoController,
        final LottoStatisticController lottoStatisticController
    ) {
        this.inputHandler = inputHandler;
        this.lottoOutputView = lottoOutputView;
        this.memberController = memberController;
        this.lottoController = lottoController;
        this.lottoStatisticController = lottoStatisticController;
    }

    public void start() {
        while(true) {
            final StartOptionNumber startOptionNumber = inputHandler.getStartOptionNumber();

            if(startOptionNumber.getValue() == FIRST_OPTION) {
                startFirstOption();
            }

            if(startOptionNumber.getValue() == SECOND_OPTION) {
                startSecondOption();
            }

            if(startOptionNumber.getValue() == THIRD_OPTION) {
                System.exit(0);
            }
        }
    }

    private void startFirstOption() {
        final Long memberId = saveMember();
        final SaveLottoStatisticDto saveLottoStatisticDto = generateLotto();
        lottoStatisticController.saveLottoStatistic(memberId, saveLottoStatisticDto);
    }

    private Long saveMember() {
        while(true) {
            try {
                final String member = inputHandler.getMemberEmail();
                return memberController.saveMember(member);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private SaveLottoStatisticDto generateLotto() {
        final LottosInfoResponseDto lottosInfo = generateLottos();

        final PurchasePrice purchasePrice = lottosInfo.getPurchasePrice();
        final Lottos lottos = lottosInfo.getLottos();
        final LottoQuantity lottoQuantity = lottosInfo.getLottoQuantity();
        lottoOutputView.outputLottos(lottoQuantity, lottos);

        final WinningRankCounter winningRankCounter = judgeRankCount(lottos);
        lottoOutputView.outputWinningResult(winningRankCounter);

        final ReturnRate returnRate = lottoController.calculateReturnRate(winningRankCounter, purchasePrice);
        lottoOutputView.outputReturnRate(returnRate);

        return new SaveLottoStatisticDto(purchasePrice, returnRate);
    }

    private LottosInfoResponseDto generateLottos() {
        while(true) {
            try {
                final String price = inputHandler.getPurchasePrice();
                final PurchaseRequestDto dto = new PurchaseRequestDto(price);
                return lottoController.generateLottos(dto);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private WinningRankCounter judgeRankCount(final Lottos lottos) {
        while(true) {
            try {
                final List<Integer> winningLottoNumbers = inputHandler.getWinningLotto();
                final String bonusNumber = inputHandler.getBonusNumber();
                final CalculateRankRequestDto dto = new CalculateRankRequestDto(lottos, winningLottoNumbers, bonusNumber);

                return lottoController.calculateWinningRank(dto);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private void startSecondOption() {
        final Long memberId = getMember();

        if(memberId == null) {
            lottoOutputView.outputInvalidMember();
            return;
        }

        final List<LottoStatisticResponseDto> lottoStatistics = lottoStatisticController.getLottoStatistics(memberId);
        lottoOutputView.outputLottoStatistics(lottoStatistics);
    }

    private Long getMember() {
        while(true) {
            try {
                final String member = inputHandler.getMemberEmail();
                return memberController.getMember(member);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }
}
