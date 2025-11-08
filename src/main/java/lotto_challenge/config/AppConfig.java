package lotto_challenge.config;

import lotto_challenge.lotto.controller.LottoController;
import lotto_challenge.lotto.view.InputView;
import lotto_challenge.lotto.view.OutputView;

public class AppConfig {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public AppConfig() {}

    public LottoController lottoController() {
        return new LottoController(inputView, outputView);
    }
}
