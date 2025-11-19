package lotto_challenge.console.config;

import lotto_challenge.core.repository.LottoStatisticRepository;
import lotto_challenge.core.repository.MemberRepository;
import lotto_challenge.core.service.LottoStatisticService;
import lotto_challenge.core.service.MemberService;
import lotto_challenge.core.repository.FakeLottoStatisticRepository;
import lotto_challenge.core.repository.FakeMemberRepository;

public class FakeAppConfig {

    private static final MemberRepository fakeMemberRepository = new FakeMemberRepository();
    private static final LottoStatisticRepository fakeLottoStatisticRepository = new FakeLottoStatisticRepository((FakeMemberRepository) fakeMemberRepository);

    private static final MemberService memberService = new MemberService(fakeMemberRepository);
    private static final LottoStatisticService lottoStatisticService = new LottoStatisticService(fakeLottoStatisticRepository);

    public FakeAppConfig() {
    }

    public static MemberService getMemberService() {
        return memberService;
    }

    public static LottoStatisticService getLottoStatisticService() {
        return lottoStatisticService;
    }
}
