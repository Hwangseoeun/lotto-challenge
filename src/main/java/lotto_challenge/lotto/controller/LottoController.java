package lotto_challenge.lotto.controller;

import lotto_challenge.lotto.generator.RandomLottoNumbersGenerator;
import lotto_challenge.lotto.generator.RandomNumberGenerator;
import lotto_challenge.lotto.model.BonusNumber;
import lotto_challenge.lotto.model.Lotto;
import lotto_challenge.lotto.model.LottoQuantity;
import lotto_challenge.lotto.model.Lottos;
import lotto_challenge.lotto.model.PurchasePrice;
import lotto_challenge.lotto.model.Rank;
import lotto_challenge.lotto.model.ReturnRate;
import lotto_challenge.lotto.model.WinningRankCounter;
import lotto_challenge.lotto.view.LottoInputView;
import lotto_challenge.lotto.view.LottoOutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;

    public LottoController(final LottoInputView lottoInputView, final LottoOutputView lottoOutputView) {
        this.lottoInputView = lottoInputView;
        this.lottoOutputView = lottoOutputView;
    }

    public void startLottoMachine() {
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
