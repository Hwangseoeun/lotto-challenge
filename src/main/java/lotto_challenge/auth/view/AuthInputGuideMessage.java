package lotto_challenge.auth.view;

public enum AuthInputGuideMessage {

    INPUT_SEPARATOR("-------------------------"),
    INPUT_START_OPTION_GUIDE_MESSAGE("원하는 옵션의 번호를 입력해 주세요."),
    INPUT_SIGN_UP_GUIDE_MESSAGE("===== 회원가입 =====\n" + "이메일을 입력하세요: "),
    INPUT_LOGIN_GUIDE_MESSAGE("===== 로그인 =====\n" + "이메일을 입력하세요: "),
    ;

    private final String message;

    AuthInputGuideMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
