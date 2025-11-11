package lotto_challenge;

import lotto_challenge.client.ConsoleClient;
import lotto_challenge.client.InputHandler;
import lotto_challenge.config.AppConfig;
import lotto_challenge.controller.LottoController;
import lotto_challenge.controller.LottoStatisticController;
import lotto_challenge.controller.MemberController;
import lotto_challenge.service.LottoService;
import lotto_challenge.service.LottoStatisticService;
import lotto_challenge.service.MemberService;

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
