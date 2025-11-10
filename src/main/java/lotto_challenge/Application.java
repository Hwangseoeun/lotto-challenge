package lotto_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

/*        final AppConfig appConfig = new AppConfig();

        final InputHandler inputHandler = appConfig.inputHandler();

        final MemberService memberService = appConfig.memberService();
        final LottoService lottoService = appConfig.lottoService();
        final LottoStatisticService lottoStatisticService = appConfig.lottoStatisticService();

        final MemberController memberController = appConfig.memberController(memberService);
        final LottoController lottoController = appConfig.lottoController(lottoService);
        final LottoStatisticController lottoStatisticController = appConfig.lottoStatisticController(lottoStatisticService);

        final ConsoleClient consoleClient = appConfig.consoleClient(inputHandler, memberController, lottoController, lottoStatisticController);

        consoleClient.start();*/

        SpringApplication.run(Application.class);
    }
}
