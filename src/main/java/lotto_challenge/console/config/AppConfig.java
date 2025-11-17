package lotto_challenge.console.config;

import lotto_challenge.console.client.ConsoleClient;
import lotto_challenge.console.client.InputHandler;
import lotto_challenge.console.view.LottoInputView;
import lotto_challenge.console.view.LottoOutputView;
import lotto_challenge.core.controller.MainController;
import lotto_challenge.core.repository.LottoStatisticRepository;
import lotto_challenge.core.repository.MemberRepository;
import lotto_challenge.core.repository.jdbc.JDBCLottoStatisticRepository;
import lotto_challenge.core.repository.jdbc.JDBCMemberRepository;
import lotto_challenge.core.service.LottoService;
import lotto_challenge.core.service.LottoStatisticService;
import lotto_challenge.core.service.MemberService;

public class AppConfig {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final MemberRepository memberRepository = new JDBCMemberRepository();
    private final LottoStatisticRepository lottoStatisticRepository = new JDBCLottoStatisticRepository();

    public AppConfig() {}

    public InputHandler inputHandler() {
        return new InputHandler(lottoInputView, lottoOutputView);
    }

    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    public LottoService lottoService() {
        return new LottoService();
    }

    public LottoStatisticService lottoStatisticService() {
        return new LottoStatisticService(lottoStatisticRepository);
    }

    public MainController mainController() {
        return new MainController(memberService(), lottoService(), lottoStatisticService());
    }

    public ConsoleClient consoleClient(
        final InputHandler inputHandler,
        final MainController mainController
    ) {
        return new ConsoleClient(
            inputHandler,
            lottoOutputView,
            mainController
        );
    }
}
