package lotto_challenge.core.model;

import java.util.List;

public class BonusNumber {

    private static final String TYPE_PATTERN = "\\d+";
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int value;

    public BonusNumber(final String number, final Lotto winningLotto) {
        validateType(number);
        final int value = Integer.parseInt(number);
        validateNumberRange(value);
        validateDuplicateNumber(value, winningLotto);

        this.value = value;
    }

    private void validateType(final String number) {
        if(!number.matches(TYPE_PATTERN)) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자로만 입력해야 해요.");
        }
    }

    private void validateNumberRange(final int number) {
        if(number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 " + MIN_NUMBER +  " ~ " + MAX_NUMBER + " 사이의 숫자만 가능해요.");
        }
    }

    public void validateDuplicateNumber(final int number, final Lotto winningLotto) {
        final List<Integer> numbers = winningLotto.getNumbers();

        if(numbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복되는 숫자는 입력할 수 없어요.");
        }
    }

    public int getValue() {
        return value;
    }
}
