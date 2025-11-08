package lotto.config;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public AppConfig() {}

    public LottoController lottoController() {
        return new LottoController(inputView, outputView);
    }
}
