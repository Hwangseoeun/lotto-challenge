package lotto_challenge.core.service;

import lotto_challenge.core.generator.RandomLottoNumbersGenerator;
import lotto_challenge.core.generator.RandomNumberGenerator;
import lotto_challenge.core.model.Lotto;
import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.Rank;
import lotto_challenge.core.model.WinningRankCounter;
import lotto_challenge.core.service.dto.GenerateLottoDto;
import lotto_challenge.core.service.dto.JudgeRankDto;
import lotto_challenge.core.service.dto.LottosDetailDto;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public LottosDetailDto generateLottos(final GenerateLottoDto dto) {
        final PurchasePrice price = new PurchasePrice(dto.purchasePrice());
        final LottoQuantity lottoQuantity = new LottoQuantity(price);

        final RandomNumberGenerator randomNumberGenerator = new RandomLottoNumbersGenerator();
        List<Lotto> lottos = new ArrayList<>();

        for(int i = 0; i < lottoQuantity.getValue(); i++) {
            final Lotto lotto = Lotto.generate(randomNumberGenerator);
            lottos.add(lotto);
        }

        final Lottos savedLottos = new Lottos(lottos);

        return new LottosDetailDto(price, lottoQuantity, savedLottos);
    }

    public void judgeRank(final JudgeRankDto dto) {
        final Lottos lottos = dto.lottos();
        final WinningRankCounter winningRankCounter = dto.winningRankCounter();

        for(Lotto buyingLotto : lottos.getValue()) {
            final boolean isMatchedBonusNumber = buyingLotto.isMatchedBonusNumber();
            final int matchedNumbersCount = buyingLotto.countMatchedNumbers();

            final Rank rank = Rank.of(matchedNumbersCount, isMatchedBonusNumber);
            winningRankCounter.increase(rank);
        }
    }
}
