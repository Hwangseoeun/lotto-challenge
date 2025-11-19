package lotto_challenge.console.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lotto_challenge.console.view.LottoOutputView;
import lotto_challenge.core.controller.dto.request.GenerateLottosRequestDto;
import lotto_challenge.core.controller.dto.request.LottoStatisticRequestDto;
import lotto_challenge.core.controller.dto.response.GenerateLottosResponseDto;
import lotto_challenge.core.controller.dto.response.LottoStatisticResponseDto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsoleClient {

    private static final int FIRST_OPTION = 1;
    private static final int SECOND_OPTION = 2;
    private static final int THIRD_OPTION = 3;

    private final InputHandler inputHandler;
    private final LottoOutputView lottoOutputView;

    public ConsoleClient(
        final InputHandler inputHandler,
        final LottoOutputView lottoOutputView
        ) {
        this.inputHandler = inputHandler;
        this.lottoOutputView = lottoOutputView;
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
                final GenerateLottosResponseDto response = generateLottosApi(request);

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

    private GenerateLottosResponseDto generateLottosApi(final GenerateLottosRequestDto request) throws Exception {
        final String url = "http://localhost:8080/api/lotto/generate";
        final ObjectMapper mapper = new ObjectMapper();

        final String requestBody = mapper.writeValueAsString(request);

        final HttpClient httpClient = HttpClient.newHttpClient();

        final HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), GenerateLottosResponseDto.class);
    }

    private void startSecondOption() {
        while(true) {
            try {
                final String member = inputHandler.getMemberEmail();

                final LottoStatisticRequestDto request = new LottoStatisticRequestDto(member);
                final LottoStatisticResponseDto response = getLottoStatisticsApi(request);

                lottoOutputView.outputLottoStatistics(response.lottoStatisticInfos());
                break;
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputInvalidMember();
            }
            catch (Exception e) {
                lottoOutputView.outputServerExceptionMessage(e);
            }
        }
    }

    private LottoStatisticResponseDto getLottoStatisticsApi(final LottoStatisticRequestDto request) throws Exception {
        final String url = "http://localhost:8080/api/lotto/statistic";
        final ObjectMapper mapper = new ObjectMapper();

        final String requestBody = mapper.writeValueAsString(request);

        final HttpClient httpClient = HttpClient.newHttpClient();

        final HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), LottoStatisticResponseDto.class);
    }
}
