package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {

        final AppConfig appConfig = new AppConfig();

        final LottoController lottoController = appConfig.lottoController();

        lottoController.startLottoMachine();
    }
}
