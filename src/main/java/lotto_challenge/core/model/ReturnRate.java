package lotto_challenge.core.model;

public class ReturnRate {

    private static final int RATE = 100;

    private final float value;

    public ReturnRate(final int totalReturn, final PurchasePrice purchasePrice) {
        this.value = calculateReturnRate(totalReturn, purchasePrice.getValue());
    }

    private float calculateReturnRate(final int totalReturn, final int purchasePrice) {
        return ((float) totalReturn / purchasePrice) * RATE;
    }

    public float getValue() {
        return value;
    }
}
