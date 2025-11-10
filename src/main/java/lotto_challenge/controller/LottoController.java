package lotto_challenge.controller;

import lotto_challenge.dto.LottosInfoResponseDto;
import lotto_challenge.model.BonusNumber;
import lotto_challenge.model.Lotto;
import lotto_challenge.model.LottoQuantity;
import lotto_challenge.model.Lottos;
import lotto_challenge.model.PurchasePrice;
import lotto_challenge.model.ReturnRate;
import lotto_challenge.model.WinningRankCounter;
import lotto_challenge.service.LottoService;

import java.util.List;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(final LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public LottosInfoResponseDto generateLottos(final String purchasePrice) {
        final PurchasePrice price = new PurchasePrice(purchasePrice);
        final LottoQuantity lottoQuantity = new LottoQuantity(price);
        final Lottos lottos = lottoService.generateLottos(lottoQuantity);

        return new LottosInfoResponseDto(price, lottoQuantity, lottos);
    }

    public WinningRankCounter calculateWinningRank(final Lottos lottos, final List<Integer> winningLottoNumbers, final String bonusNumber) {
        final Lotto winningLotto = new Lotto(winningLottoNumbers);
        final BonusNumber bonus = new BonusNumber(bonusNumber, winningLotto);

        final WinningRankCounter winningRankCounter = new WinningRankCounter();
        lottoService.judgeRank(winningRankCounter, lottos, winningLotto, bonus);

        return winningRankCounter;
    }

    public ReturnRate calculateReturnRate(final WinningRankCounter winningRankCounter, final PurchasePrice purchasePrice) {
        final int totalReturn = winningRankCounter.calculateReturn();
        return new ReturnRate(totalReturn, purchasePrice);
    }
}
