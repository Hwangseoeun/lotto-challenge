package lotto_challenge.service;

import lotto_challenge.generator.RandomLottoNumbersGenerator;
import lotto_challenge.generator.RandomNumberGenerator;
import lotto_challenge.model.BonusNumber;
import lotto_challenge.model.Lotto;
import lotto_challenge.model.LottoQuantity;
import lotto_challenge.model.Lottos;
import lotto_challenge.model.Rank;
import lotto_challenge.model.WinningRankCounter;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public Lottos generateLottos(final LottoQuantity lottoQuantity) {
        final RandomNumberGenerator randomNumberGenerator = new RandomLottoNumbersGenerator();
        List<Lotto> lottos = new ArrayList<>();

        for(int i = 0; i < lottoQuantity.getValue(); i++) {
            final Lotto lotto = Lotto.generate(randomNumberGenerator);
            lottos.add(lotto);
        }

        return new Lottos(lottos);
    }

    public void judgeRank(
        final WinningRankCounter winningRankCounter,
        final Lottos lottos,
        final Lotto winningLotto,
        final BonusNumber bonusNumber
    ) {
        for(Lotto buyingLotto : lottos.getValue()) {
            final boolean isMatchedBonusNumber = buyingLotto.isMatchedBonusNumber(bonusNumber);
            final int matchedNumbersCount = buyingLotto.countMatchedNumbers(winningLotto);

            final Rank rank = Rank.of(matchedNumbersCount, isMatchedBonusNumber);
            winningRankCounter.increase(rank);
        }
    }
}
