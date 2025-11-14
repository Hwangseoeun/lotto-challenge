package lotto_challenge.core.service.dto;

import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;

public record LottosDetailDto(

    PurchasePrice purchasePrice,
    LottoQuantity lottoQuantity,
    Lottos lottos

) {
}
