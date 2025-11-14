package lotto_challenge.client;

import lotto_challenge.console.client.StartOptionNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StartOptionNumberTest {

    private static StartOptionNumber createStartOptionNumber(final String startOption) {
        return new StartOptionNumber(startOption);
    }

    @DisplayName("생성자에 유효한 입력값(옵션 번호)을 입력하면 StartOptionNumber 인스턴스가 생성된다.")
    @Test
    void givenNumber_whenCreateStartOptionNumber_thenSuccess() {
        //Given
        final String number = "1";

        //When & Then
        assertThatCode(() -> createStartOptionNumber(number))
            .doesNotThrowAnyException();
    }

    @DisplayName("공백이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenNumberIsNull() {
        //Given
        final String number = null;

        //When & Then
        assertThatThrownBy(() -> createStartOptionNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 공백은 입력할 수 없어요.");
    }

    @DisplayName("숫자가 아닌 값이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenNumberTypeIsInvalid() {
        //Given
        final String number = "A";

        //When & Then
        assertThatThrownBy(() -> createStartOptionNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 시작 옵션은 숫자로만 입력해야 해요.");
    }

    @DisplayName("유효하지 않은 선택지가 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenInvalidStartOption() {
        //Given
        final String number = "5";

        //When & Then
        assertThatThrownBy(() -> createStartOptionNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 선택지에요.");
    }
}