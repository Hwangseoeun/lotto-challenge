package lotto_challenge.console.client;

import lotto_challenge.console.view.LottoOutputView;
import lotto_challenge.core.controller.MainController;
import lotto_challenge.core.controller.dto.request.GenerateLottosRequestDto;
import lotto_challenge.core.controller.dto.request.LottoStatisticRequestDto;
import lotto_challenge.core.controller.dto.response.GenerateLottosResponseDto;
import lotto_challenge.core.controller.dto.response.LottoStatisticResponseDto;

public class ConsoleClient {

    private static final int FIRST_OPTION = 1;
    private static final int SECOND_OPTION = 2;
    private static final int THIRD_OPTION = 3;

    private final InputHandler inputHandler;
    private final LottoOutputView lottoOutputView;
    private final MainController mainController;

    public ConsoleClient(
        final InputHandler inputHandler,
        final LottoOutputView lottoOutputView,
        final MainController mainController
        ) {
        this.inputHandler = inputHandler;
        this.lottoOutputView = lottoOutputView;
        this.mainController = mainController;
    }

    public void start() {
        while(true) {
            final String number = inputHandler.getStartOptionNumber();
            final StartOptionNumber startOptionNumber = new StartOptionNumber(number);

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
        final String member = inputHandler.getMemberEmail();
        final String price = inputHandler.getPurchasePrice();

        final GenerateLottosRequestDto request = new GenerateLottosRequestDto(member, price);
        final GenerateLottosResponseDto response = mainController.generateLottos(request);

        lottoOutputView.outputLottos(response.lottoQuantity(), response.lottos());
        lottoOutputView.outputWinningResult(response.winningRankCounter());
        lottoOutputView.outputReturnRate(response.returnRate());
    }

    private void startSecondOption() {
        while(true) {
            try {
                final String member = inputHandler.getMemberEmail();

                final LottoStatisticRequestDto request = new LottoStatisticRequestDto(member);
                final LottoStatisticResponseDto response = mainController.getLottoStatistics(request);

                lottoOutputView.outputLottoStatistics(response.lottoStatisticInfos());
                break;
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputInvalidMember();
            }
        }
    }
}
