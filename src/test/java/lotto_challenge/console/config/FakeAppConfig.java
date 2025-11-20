package lotto_challenge.console.config;

import lotto_challenge.core.repository.FakeLottoStatisticRepository;
import lotto_challenge.core.repository.FakeMemberRepository;
import lotto_challenge.core.service.LottoStatisticService;
import lotto_challenge.core.service.MemberService;

public class FakeAppConfig {

    public FakeAppConfig() {
    }

    public FakeMemberRepository createFakeMemberRepository() {
        return new FakeMemberRepository();
    }

    public FakeLottoStatisticRepository createFakeLottoStatisticRepository(FakeMemberRepository fakeMemberRepository) {
        return new FakeLottoStatisticRepository(fakeMemberRepository);
    }

    public MemberService createMemberService(FakeMemberRepository fakeMemberRepository) {
        return new MemberService(fakeMemberRepository);
    }

    public LottoStatisticService createLottoStatisticService(FakeLottoStatisticRepository fakeLottoStatisticRepository) {
        return new LottoStatisticService(fakeLottoStatisticRepository);
    }
}
