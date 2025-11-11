package lotto_challenge.core.dto;

import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;

public record LottosInfoResponseDto(

    PurchasePrice getPurchasePrice,
    LottoQuantity getLottoQuantity,
    Lottos getLottos

) {
}
