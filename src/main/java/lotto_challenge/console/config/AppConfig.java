package lotto_challenge.console.config;

import lotto_challenge.console.client.ConsoleClient;
import lotto_challenge.console.client.InputHandler;
import lotto_challenge.console.client.api.ApiClient;
import lotto_challenge.console.client.api.HttpApiClient;
import lotto_challenge.console.view.LottoInputView;
import lotto_challenge.console.view.LottoOutputView;

public class AppConfig {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();

    public InputHandler inputHandler() {
        return new InputHandler(lottoInputView, lottoOutputView);
    }

    public ApiClient apiClient() {
        return new HttpApiClient();
    }

    public ConsoleClient consoleClient(final InputHandler inputHandler, final ApiClient apiClient) {
        return new ConsoleClient(
            inputHandler,
            lottoOutputView,
            apiClient
        );
    }
}
