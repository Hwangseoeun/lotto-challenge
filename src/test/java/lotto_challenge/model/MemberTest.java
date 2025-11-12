package lotto_challenge.model;

import lotto_challenge.core.model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    private static Member createMember(final String email) {
        return new Member(email);
    }

    @DisplayName("생성자에 유효한 입력값(이메일 문자열)을 입력하면 Member 인스턴스가 생성된다.")
    @Test
    void givenEmail_whenCreateMember_thenSuccess() {
        //Given
        final String email = "test@gmail.com";

        //When & Then
        assertThatCode(() -> createMember(email))
            .doesNotThrowAnyException();
    }

    @DisplayName("공백이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenEmailIsNull() {
        //Given
        final String email = "";

        //When & Then
        assertThatThrownBy(() -> createMember(email))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 이메일에 공백은 입력할 수 없어요.");
    }

    @DisplayName("올바르지 않은 이메일 형식이 들어오면 예외가 발생한다.")
    @MethodSource
    @ParameterizedTest
    void thrownException_givenInvalidEmailType_whenCreateMember(final String email) {
        //When & Then
        assertThatThrownBy(() -> createMember(email))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 올바르지 않은 이메일 형식이에요.");
    }

    private static Stream<String> thrownException_givenInvalidEmailType_whenCreateMember() {
        return Stream.of(
            "test",
            "test@gmail",
            "testgmail.com"
        );
    }
}