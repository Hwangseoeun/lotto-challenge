package lotto_challenge.core.model;

public class PurchasePrice {

    private static final String TYPE_PATTERN = "^\\d+$";
    private static final int UNIT = 1000;

    private final int value;

    public PurchasePrice(final String price) {
        validateType(price);
        final int value = Integer.parseInt(price);
        validateUnit(value);

        this.value = value;
    }

    private void validateType(final String price) {
        if(!price.matches(TYPE_PATTERN)) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자로만 입력해야 해요.");
        }
    }

    private void validateUnit(final int price) {
        if(price % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + UNIT + "원 단위로만 가능해요.");
        }
    }

    public int getValue() {
        return value;
    }
}
