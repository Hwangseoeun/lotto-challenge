package lotto_challenge.console.config;

import lotto_challenge.console.client.ConsoleClient;
import lotto_challenge.console.client.InputHandler;
import lotto_challenge.core.controller.LottoController;
import lotto_challenge.core.controller.LottoStatisticController;
import lotto_challenge.core.controller.MemberController;
import lotto_challenge.core.repository.LottoStatisticRepository;
import lotto_challenge.core.repository.MemberRepository;
import lotto_challenge.core.service.LottoService;
import lotto_challenge.core.service.LottoStatisticService;
import lotto_challenge.core.service.MemberService;
import lotto_challenge.console.view.LottoInputView;
import lotto_challenge.console.view.LottoOutputView;

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

    public MemberController memberController(final MemberService memberService) {
        return new MemberController(memberService);
    }

    public LottoController lottoController(final LottoService lottoService) {
        return new LottoController(lottoService);
    }

    public LottoStatisticController lottoStatisticController(final LottoStatisticService lottoStatisticService) {
        return new LottoStatisticController(lottoStatisticService);
    }

    public ConsoleClient consoleClient(
        final InputHandler inputHandler,
        final MemberController memberController,
        final LottoController lottoController,
        final LottoStatisticController lottoStatisticController
    ) {
        return new ConsoleClient(
            inputHandler,
            lottoOutputView,
            memberController,
            lottoController,
            lottoStatisticController
        );
    }
}
