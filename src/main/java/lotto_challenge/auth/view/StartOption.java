package lotto_challenge.auth.view;

public enum StartOption {

    SIGN_UP(1, "회원가입"),
    LOGIN(2, "로그인"),
    EXIT(3, "종료"),
    ;

    private final int optionNumber;
    private final String description;

    StartOption(final int optionNumber, final String description) {
        this.optionNumber = optionNumber;
        this.description = description;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public String getDescription() {
        return description;
    }
}
