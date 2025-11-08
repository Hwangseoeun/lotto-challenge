package lotto_challenge;

import lotto_challenge.auth.controller.AuthController;
import lotto_challenge.config.AppConfig;
import lotto_challenge.lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {

        final AppConfig appConfig = new AppConfig();

        final AuthController authController = appConfig.authController();
        final LottoController lottoController = appConfig.lottoController();

        authController.startAuth();

//        lottoController.startLottoMachine();
    }
}
