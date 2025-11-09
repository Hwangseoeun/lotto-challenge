package lotto_challenge.dto;

import lotto_challenge.model.PurchasePrice;
import lotto_challenge.model.ReturnRate;

public record LottoStatisticSaveRequestDto(

    PurchasePrice purchasePrice,
    ReturnRate returnRate

) {
    public LottoStatisticSaveRequestDto(
        final PurchasePrice purchasePrice,
        final ReturnRate returnRate
    ) {
        this.purchasePrice = purchasePrice;
        this.returnRate = returnRate;
    }
}
