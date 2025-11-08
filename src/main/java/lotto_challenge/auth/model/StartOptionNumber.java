package lotto_challenge.auth.model;

public class StartOptionNumber {

    private static final String TYPE_PATTERN = "[0-9]";
    private static final int MIN_OPTION_NUMBER = 1;
    private static final int MAX_OPTION_NUMBER = 3;

    private final int value;

    public StartOptionNumber(final String chooseOption) {
        validateChooseOptionContainsBlank(chooseOption);
        validateType(chooseOption);
        final int optionNumber = Integer.parseInt(chooseOption);
        validateOption(optionNumber);

        this.value = optionNumber;
    }

    private void validateChooseOptionContainsBlank(final String chooseOption) {
        if(chooseOption == null || chooseOption.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력할 수 없어요.");
        }
    }

    private void validateType(final String chooseOption) {
        if(!chooseOption.matches(TYPE_PATTERN)) {
            throw new IllegalArgumentException("[ERROR] 시작 옵션은 숫자로만 입력해야 해요.");
        }
    }

    private void validateOption(final int optionNumber) {
        if(optionNumber < MIN_OPTION_NUMBER || optionNumber > MAX_OPTION_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 선택지에요.");
        }
    }

    public int getValue() {
        return value;
    }
}
