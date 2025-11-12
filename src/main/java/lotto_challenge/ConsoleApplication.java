package lotto_challenge;

import lotto_challenge.console.client.ConsoleClient;
import lotto_challenge.console.client.InputHandler;
import lotto_challenge.console.config.AppConfig;
import lotto_challenge.core.controller.LottoController;
import lotto_challenge.core.controller.LottoStatisticController;
import lotto_challenge.core.controller.MemberController;
import lotto_challenge.core.service.LottoService;
import lotto_challenge.core.service.LottoStatisticService;
import lotto_challenge.core.service.MemberService;

public class ConsoleApplication {
    public static void main(String[] args) {

        final AppConfig appConfig = new AppConfig();

        final InputHandler inputHandler = appConfig.inputHandler();

        final MemberService memberService = appConfig.memberService();
        final LottoService lottoService = appConfig.lottoService();
        final LottoStatisticService lottoStatisticService = appConfig.lottoStatisticService();

        final MemberController memberController = appConfig.memberController(memberService);
        final LottoController lottoController = appConfig.lottoController(lottoService);
        final LottoStatisticController lottoStatisticController = appConfig.lottoStatisticController(lottoStatisticService);

        final ConsoleClient consoleClient = appConfig.consoleClient(inputHandler, memberController, lottoController, lottoStatisticController);

        consoleClient.start();
    }
}
