package lotto_challenge.lotto.view;

public enum InputGuideMessage {

    INPUT_PURCHASE_PRICE_GUIDE_MESSAGE("구입금액을 입력해 주세요."),
    INPUT_WINNING_LOTTO_NUMBER_GUIDE_MESSAGE("당첨 번호를 입력해 주세요."),
    INPUT_WINNING_BONUS_NUMBER_GUIDE_MESSAGE("보너스 번호를 입력해 주세요.")
    ;

    private final String message;

    InputGuideMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
