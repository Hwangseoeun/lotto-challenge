package lotto_challenge.dto;

import lotto_challenge.model.PurchasePrice;
import lotto_challenge.model.ReturnRate;

public record LottoStatisticDto(

    PurchasePrice purchasePrice,
    ReturnRate returnRate

) {
    public LottoStatisticDto(
        final PurchasePrice purchasePrice,
        final ReturnRate returnRate
    ) {
        this.purchasePrice = purchasePrice;
        this.returnRate = returnRate;
    }
}
