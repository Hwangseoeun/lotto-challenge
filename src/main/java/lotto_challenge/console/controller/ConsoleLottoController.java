package lotto_challenge.console.controller;

import lotto_challenge.core.controller.LottoController;
import lotto_challenge.core.dto.LottosInfoResponseDto;
import lotto_challenge.core.model.BonusNumber;
import lotto_challenge.core.model.Lotto;
import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;
import lotto_challenge.core.service.LottoService;

import java.util.List;

public class ConsoleLottoController implements LottoController {

    private final LottoService lottoService;

    public ConsoleLottoController(final LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @Override
    public LottosInfoResponseDto generateLottos(final String purchasePrice) {
        final PurchasePrice price = new PurchasePrice(purchasePrice);
        final LottoQuantity lottoQuantity = new LottoQuantity(price);
        final Lottos lottos = lottoService.generateLottos(lottoQuantity);

        return new LottosInfoResponseDto(price, lottoQuantity, lottos);
    }

    @Override
    public WinningRankCounter calculateWinningRank(final Lottos lottos, final List<Integer> winningLottoNumbers, final String bonusNumber) {
        final Lotto winningLotto = new Lotto(winningLottoNumbers);
        final BonusNumber bonus = new BonusNumber(bonusNumber, winningLotto);

        final WinningRankCounter winningRankCounter = new WinningRankCounter();
        lottoService.judgeRank(winningRankCounter, lottos, winningLotto, bonus);

        return winningRankCounter;
    }

    @Override
    public ReturnRate calculateReturnRate(final WinningRankCounter winningRankCounter, final PurchasePrice purchasePrice) {
        final int totalReturn = winningRankCounter.calculateReturn();
        return new ReturnRate(totalReturn, purchasePrice);
    }
}
