package lotto_challenge.core.service.dto;

import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;

public record SaveLottoStatisticDto(

    Long memberId,
    PurchasePrice getPurchasePrice,
    ReturnRate getReturnRate

) {
}
