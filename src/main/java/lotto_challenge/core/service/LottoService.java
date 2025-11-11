package lotto_challenge.core.service;

import lotto_challenge.core.generator.RandomLottoNumbersGenerator;
import lotto_challenge.core.generator.RandomNumberGenerator;
import lotto_challenge.core.model.BonusNumber;
import lotto_challenge.core.model.Lotto;
import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.Rank;
import lotto_challenge.core.model.WinningRankCounter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
