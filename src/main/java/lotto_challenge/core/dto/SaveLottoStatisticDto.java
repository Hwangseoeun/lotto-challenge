package lotto_challenge.core.dto;

import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;

public record SaveLottoStatisticDto(

    PurchasePrice getPurchasePrice,
    ReturnRate getReturnRate

) {
    public SaveLottoStatisticDto(
        final PurchasePrice getPurchasePrice,
        final ReturnRate getReturnRate
    ) {
        this.getPurchasePrice = getPurchasePrice;
        this.getReturnRate = getReturnRate;
    }
}
