package lotto_challenge.console.config;

import lotto_challenge.console.client.ConsoleClient;
import lotto_challenge.console.client.InputHandler;
import lotto_challenge.console.view.LottoInputView;
import lotto_challenge.console.view.LottoOutputView;

public class AppConfig {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();

    public AppConfig() {}

    public InputHandler inputHandler() {
        return new InputHandler(lottoInputView, lottoOutputView);
    }

    public ConsoleClient consoleClient(final InputHandler inputHandler) {
        return new ConsoleClient(
            inputHandler,
            lottoOutputView
        );
    }
}
