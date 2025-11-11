package lotto_challenge.core.controller;

import lotto_challenge.dto.LottosInfoResponseDto;
import lotto_challenge.model.Lottos;
import lotto_challenge.model.PurchasePrice;
import lotto_challenge.model.ReturnRate;
import lotto_challenge.model.WinningRankCounter;

import java.util.List;

public interface LottoController {

    LottosInfoResponseDto generateLottos(final String purchasePrice);
    WinningRankCounter calculateWinningRank(final Lottos lottos, final List<Integer> winningLottoNumbers, final String bonusNumber);
    ReturnRate calculateReturnRate(final WinningRankCounter winningRankCounter, final PurchasePrice purchasePrice);
}
