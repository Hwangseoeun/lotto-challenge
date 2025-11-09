package lotto_challenge.controller;

import lotto_challenge.generator.RandomLottoNumbersGenerator;
import lotto_challenge.generator.RandomNumberGenerator;
import lotto_challenge.model.BonusNumber;
import lotto_challenge.model.Lotto;
import lotto_challenge.model.LottoQuantity;
import lotto_challenge.model.Lottos;
import lotto_challenge.model.Member;
import lotto_challenge.model.PurchasePrice;
import lotto_challenge.model.Rank;
import lotto_challenge.model.ReturnRate;
import lotto_challenge.model.StartOptionNumber;
import lotto_challenge.model.WinningRankCounter;
import lotto_challenge.repository.MemberRepository;
import lotto_challenge.view.LottoInputView;
import lotto_challenge.view.LottoOutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final int FIRST_OPTION = 1;
    private static final int SECOND_OPTION = 2;
    private static final int THIRD_OPTION = 3;

    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;
    private final MemberRepository memberRepository;

    public LottoController(final LottoInputView lottoInputView, final LottoOutputView lottoOutputView, final MemberRepository memberRepository) {
        this.lottoInputView = lottoInputView;
        this.lottoOutputView = lottoOutputView;
        this.memberRepository = memberRepository;
    }

    public void start() {
        while(true) {
            final StartOptionNumber startOptionNumber = getStartOptionNumber();

            if(startOptionNumber.getValue() == FIRST_OPTION) {
                final Member member = getMemberEmail();
                memberRepository.save(member);
                generateLotto();
            }

            if(startOptionNumber.getValue() == SECOND_OPTION) {
                final Member member = getMemberEmail();
                // 사용자가 입력한 이메일을 기반으로 그동안의 수익률을 조회하는 기능 추가 예정
            }

            if(startOptionNumber.getValue() == THIRD_OPTION) {
                System.exit(0);
            }
        }
    }

    private Member getMemberEmail() {
        while(true) {
            try {
                final String email = lottoInputView.inputMemberEmail();
                return new Member(email);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private void generateLotto() {
        final PurchasePrice purchasePrice = getPurchasePrice();
        final LottoQuantity lottoQuantity = new LottoQuantity(purchasePrice);

        final Lottos lottos = generateLottos(lottoQuantity);
        lottoOutputView.outputLottos(lottoQuantity, lottos);

        final Lotto winningLotto = getWinningLotto();
        final BonusNumber bonusNumber = getBonusNumber(winningLotto);

        final WinningRankCounter winningRankCounter = new WinningRankCounter();
        judgeRank(winningRankCounter, lottos, winningLotto, bonusNumber);
        lottoOutputView.outputWinningResult(winningRankCounter);

        final int totalReturn = winningRankCounter.calculateReturn();
        final ReturnRate returnRate = new ReturnRate(totalReturn, purchasePrice);
        lottoOutputView.outputReturnRate(returnRate);
    }

    private StartOptionNumber getStartOptionNumber() {
        while(true) {
            try {
                final String startOption = lottoInputView.inputStartOption();
                return new StartOptionNumber(startOption);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private PurchasePrice getPurchasePrice() {
        while(true) {
            try {
                final String purchasePrice = lottoInputView.inputPurchasePrice();
                return new PurchasePrice(purchasePrice);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private Lottos generateLottos(final LottoQuantity lottoQuantity) {
        final RandomNumberGenerator randomNumberGenerator = new RandomLottoNumbersGenerator();
        List<Lotto> lottos = new ArrayList<>();

        for(int i = 0; i < lottoQuantity.getValue(); i++) {
            final Lotto lotto = Lotto.generate(randomNumberGenerator);
            lottos.add(lotto);
        }

        return new Lottos(lottos);
    }

    private Lotto getWinningLotto() {
        while(true) {
            try {
                final List<Integer> winningLotto = lottoInputView.inputWinningLotto();
                return new Lotto(winningLotto);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private BonusNumber getBonusNumber(final Lotto winningLotto) {
        while(true) {
            try {
                final String bonusNumber = lottoInputView.inputWinningBonusNumber();
                return new BonusNumber(bonusNumber, winningLotto);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    private void judgeRank(final WinningRankCounter winningRankCounter, final Lottos lottos, final Lotto winningLotto, final BonusNumber bonusNumber) {
        for(Lotto buyingLotto : lottos.getValue()) {
            final boolean isMatchedBonusNumber = buyingLotto.isMatchedBonusNumber(bonusNumber);
            final int matchedNumbersCount = buyingLotto.countMatchedNumbers(winningLotto);

            final Rank rank = Rank.of(matchedNumbersCount, isMatchedBonusNumber);
            winningRankCounter.increase(rank);
        }
    }
}
