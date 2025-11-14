package lotto_challenge.console.view;

public enum LottoInputGuideMessage {

    INPUT_START_OPTION_GUIDE_MESSAGE("선택지의 번호를 입력해 주세요."),
    FIRST_OPTION_MESSAGE("1. 새로운 로또 발행하기"),
    SECOND_OPTION_MESSAGE("2. 그동안의 당첨 통계 조회하기"),
    THIRD_OPTION_MESSAGE("3. 종료"),
    INPUT_SEPARATOR("------------------"),
    INPUT_MEMBER_EMAIL_GUIDE_MESSAGE("저장 혹은 조회할 이메일을 입력해주세요."),
    INPUT_PURCHASE_PRICE_GUIDE_MESSAGE("구입금액을 입력해 주세요."),
    INPUT_WINNING_LOTTO_NUMBER_GUIDE_MESSAGE("당첨 번호를 입력해 주세요."),
    INPUT_WINNING_BONUS_NUMBER_GUIDE_MESSAGE("보너스 번호를 입력해 주세요."),
    ;

    private final String message;

    LottoInputGuideMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
