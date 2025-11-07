package lotto.model;

public class LottoQuantity {

    private static final int LOTTO_PRICE = 1000;

    private final int value;

    public LottoQuantity(final PurchasePrice purchasePrice) {
        this.value = calculateQuantity(purchasePrice.getValue());
    }

    private int calculateQuantity(final int purchasePrice) {
        return purchasePrice / LOTTO_PRICE;
    }

    public int getValue() {
        return value;
    }
}
