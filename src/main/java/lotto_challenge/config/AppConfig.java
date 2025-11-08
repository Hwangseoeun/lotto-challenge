package lotto_challenge.config;

import lotto_challenge.controller.LottoController;
import lotto_challenge.view.LottoInputView;
import lotto_challenge.view.LottoOutputView;

public class AppConfig {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();

    public AppConfig() {}

    public LottoController lottoController() {
        return new LottoController(lottoInputView, lottoOutputView);
    }
}
