package lotto_challenge.core.controller;

import lotto_challenge.core.dto.CalculateRankRequestDto;
import lotto_challenge.core.dto.LottosInfoResponseDto;
import lotto_challenge.core.dto.PurchaseRequestDto;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;

public interface LottoController {

    LottosInfoResponseDto generateLottos(final PurchaseRequestDto dto);
    WinningRankCounter calculateWinningRank(final CalculateRankRequestDto dto);
    ReturnRate calculateReturnRate(final WinningRankCounter winningRankCounter, final PurchasePrice purchasePrice);
}
