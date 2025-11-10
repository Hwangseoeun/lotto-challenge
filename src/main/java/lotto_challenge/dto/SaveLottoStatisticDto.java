package lotto_challenge.dto;

import lotto_challenge.model.PurchasePrice;
import lotto_challenge.model.ReturnRate;

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
