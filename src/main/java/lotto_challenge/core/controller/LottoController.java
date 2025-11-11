package lotto_challenge.core.controller;

import lotto_challenge.core.dto.LottosInfoResponseDto;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;

import java.util.List;

public interface LottoController {

    LottosInfoResponseDto generateLottos(final String purchasePrice);
    WinningRankCounter calculateWinningRank(final Lottos lottos, final List<Integer> winningLottoNumbers, final String bonusNumber);
    ReturnRate calculateReturnRate(final WinningRankCounter winningRankCounter, final PurchasePrice purchasePrice);
}
