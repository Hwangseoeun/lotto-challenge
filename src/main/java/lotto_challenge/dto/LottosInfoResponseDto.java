package lotto_challenge.dto;

import lotto_challenge.model.LottoQuantity;
import lotto_challenge.model.Lottos;
import lotto_challenge.model.PurchasePrice;

public record LottosInfoResponseDto(

    PurchasePrice getPurchasePrice,
    LottoQuantity getLottoQuantity,
    Lottos getLottos

) {
}
