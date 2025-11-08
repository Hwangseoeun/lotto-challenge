package lotto_challenge;

import lotto_challenge.config.AppConfig;
import lotto_challenge.controller.LottoController;

public class Application {
    public static void main(String[] args) {

        final AppConfig appConfig = new AppConfig();

        final LottoController lottoController = appConfig.lottoController();

        lottoController.startLottoMachine();
    }
}
