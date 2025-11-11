package lotto_challenge.config;

import lotto_challenge.client.ConsoleClient;
import lotto_challenge.client.InputHandler;
import lotto_challenge.console.controller.ConsoleLottoController;
import lotto_challenge.console.controller.ConsoleLottoStatisticController;
import lotto_challenge.console.controller.ConsoleMemberController;
import lotto_challenge.core.controller.LottoController;
import lotto_challenge.core.controller.LottoStatisticController;
import lotto_challenge.core.controller.MemberController;
import lotto_challenge.repository.LottoStatisticRepository;
import lotto_challenge.repository.MemberRepository;
import lotto_challenge.service.LottoService;
import lotto_challenge.service.LottoStatisticService;
import lotto_challenge.service.MemberService;
import lotto_challenge.view.LottoInputView;
import lotto_challenge.view.LottoOutputView;

public class AppConfig {

    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final MemberRepository memberRepository = new MemberRepository();
    private final LottoStatisticRepository lottoStatisticRepository = new LottoStatisticRepository();

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

    public MemberController consoleMemberController(final MemberService memberService) {
        return new ConsoleMemberController(memberService);
    }

    public LottoController consoleLottoController(final LottoService lottoService) {
        return new ConsoleLottoController(lottoService);
    }

    public LottoStatisticController consoleLottoStatisticController(final LottoStatisticService lottoStatisticService) {
        return new ConsoleLottoStatisticController(lottoStatisticService);
    }

    public ConsoleClient consoleClient(
        final InputHandler inputHandler,
        final MemberController consoleMemberController,
        final LottoController consoleLottoController,
        final LottoStatisticController consoleLottoStatisticController
    ) {
        return new ConsoleClient(
            inputHandler,
            lottoOutputView,
            consoleMemberController,
            consoleLottoController,
            consoleLottoStatisticController
        );
    }
}
