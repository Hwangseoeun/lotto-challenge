package lotto_challenge.core.model;

public class Member {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9.-]+$";

    private final String email;

    public Member(final String email) {
        validateEmailContainsBlank(email);
        validateEmailType(email);

        this.email = email;
    }

    private void validateEmailContainsBlank(final String email) {
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이메일에 공백은 입력할 수 없어요.");
        }
    }

    private void validateEmailType(final String email) {
        if(!email.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 이메일 형식이에요.");
        }
    }

    public String getEmail() {
        return email;
    }
}
