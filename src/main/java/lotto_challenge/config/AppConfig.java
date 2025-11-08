package lotto_challenge.config;

import lotto_challenge.auth.controller.AuthController;
import lotto_challenge.auth.view.AuthInputView;
import lotto_challenge.auth.view.AuthOutputView;
import lotto_challenge.lotto.controller.LottoController;
import lotto_challenge.lotto.view.LottoInputView;
import lotto_challenge.lotto.view.LottoOutputView;

public class AppConfig {

    private final AuthInputView authInputView = new AuthInputView();
    private final AuthOutputView authOutputView = new AuthOutputView();
    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();

    public AppConfig() {}

    public AuthController authController() {
        return new AuthController(authInputView, authOutputView);
    }

    public LottoController lottoController() {
        return new LottoController(lottoInputView, lottoOutputView);
    }
}
