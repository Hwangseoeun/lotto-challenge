package lotto.controller;

import lotto.generator.RandomLottoNumbersGenerator;
import lotto.generator.RandomNumberGenerator;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoQuantity;
import lotto.model.Lottos;
import lotto.model.PurchasePrice;
import lotto.model.Rank;
import lotto.model.ReturnRate;
import lotto.model.WinningRankCounter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startLottoMachine() {
        final PurchasePrice purchasePrice = getPurchasePrice();
        final LottoQuantity lottoQuantity = new LottoQuantity(purchasePrice);

        final Lottos lottos = generateLottos(lottoQuantity);
        outputView.outputLottos(lottoQuantity, lottos);

        final Lotto winningLotto = getWinningLotto();
        final BonusNumber bonusNumber = getBonusNumber(winningLotto);

        final WinningRankCounter winningRankCounter = new WinningRankCounter();
        judgeRank(winningRankCounter, lottos, winningLotto, bonusNumber);
        outputView.outputWinningResult(winningRankCounter);

        final int totalReturn = winningRankCounter.calculateReturn();
        final ReturnRate returnRate = new ReturnRate(totalReturn, purchasePrice);
        outputView.outputReturnRate(returnRate);
    }

    private PurchasePrice getPurchasePrice() {
        while(true) {
            try {
                final String purchasePrice = inputView.inputPurchasePrice();
                return new PurchasePrice(purchasePrice);
            }
            catch (IllegalArgumentException e) {
                outputView.outputExceptionMessage(e);
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
                final List<Integer> winningLotto = inputView.inputWinningLotto();
                return new Lotto(winningLotto);
            }
            catch (IllegalArgumentException e) {
                outputView.outputExceptionMessage(e);
            }
        }
    }

    private BonusNumber getBonusNumber(final Lotto winningLotto) {
        while(true) {
            try {
                final String bonusNumber = inputView.inputWinningBonusNumber();
                return new BonusNumber(bonusNumber, winningLotto);
            }
            catch (IllegalArgumentException e) {
                outputView.outputExceptionMessage(e);
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
