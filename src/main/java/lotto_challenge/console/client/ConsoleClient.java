package lotto_challenge.console.client;

import lotto_challenge.console.client.api.ApiClient;
import lotto_challenge.console.view.LottoOutputView;
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
    private final ApiClient apiClient;

    public ConsoleClient(
        final InputHandler inputHandler,
        final LottoOutputView lottoOutputView,
        final ApiClient apiClient
        ) {
        this.inputHandler = inputHandler;
        this.lottoOutputView = lottoOutputView;
        this.apiClient = apiClient;
    }

    public void start() {
        while(true) {
            try {
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
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private void startFirstOption() {
        while(true) {
            try {
                final String member = inputHandler.getMemberEmail();
                final String price = inputHandler.getPurchasePrice();

                final GenerateLottosRequestDto request = new GenerateLottosRequestDto(member, price);
                final GenerateLottosResponseDto response = apiClient.generateLottosApi(request);

                lottoOutputView.outputLottos(response.lottoQuantity(), response.lottos());
                lottoOutputView.outputWinningResult(response.winningRanks());
                lottoOutputView.outputReturnRate(response.returnRate());
                break;
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
            catch (Exception e) {
                lottoOutputView.outputServerExceptionMessage(e);
            }
        }
    }

    private void startSecondOption() {
        while (true) {
            try {
                final String member = inputHandler.getMemberEmail();

                final LottoStatisticRequestDto request = new LottoStatisticRequestDto(member);
                final LottoStatisticResponseDto response = apiClient.getLottoStatisticsApi(request);

                lottoOutputView.outputLottoStatistics(response.lottoStatisticInfos());
                break;
            } catch (IllegalArgumentException e) {
                lottoOutputView.outputInvalidMember();
            } catch (Exception e) {
                lottoOutputView.outputServerExceptionMessage(e);
            }
        }
    }
}
