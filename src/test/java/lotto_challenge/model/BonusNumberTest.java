package lotto_challenge.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {

    private static BonusNumber createBonusNumber(final String number, final Lotto buyingLotto) {
        return new BonusNumber(number, buyingLotto);
    }

    private static Lotto createLotto(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    @DisplayName("생성자에 유효한 입력값(숫자 형태의 문자열과 당첨 로또)을 입력하면 BonusNumber 인스턴스가 생성된다.")
    @Test
    void givenNumberAndWinningLotto_whenCreateBonusNumber_thenSuccess() {
        //Given
        final String number = "45";
        final Lotto winningLotto = createLotto(List.of(1, 2, 3, 4, 5, 6));

        //When & Then
        assertThatCode(() -> createBonusNumber(number, winningLotto))
            .doesNotThrowAnyException();
    }

    @DisplayName("숫자가 아닌 다른 값이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenNumberIsInvalid() {
        //Given
        final String number = "45j";
        final Lotto winningLotto = createLotto(List.of(1, 2, 3, 4, 5, 6));

        //When & Then
        assertThatThrownBy(() -> createBonusNumber(number, winningLotto))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 구입 금액은 숫자로만 입력해야 해요.");
    }

    @DisplayName("1 ~ 45 사이의 숫자가 아닌 다른 값이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenNumberIsLimitOver() {
        //Given
        final String number = "50";
        final Lotto winningLotto = createLotto(List.of(1, 2, 3, 4, 5, 6));

        //When & Then
        assertThatThrownBy(() -> createBonusNumber(number, winningLotto))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 보너스 번호는 1 ~ 45 사이의 숫자만 가능해요.");
    }

    @DisplayName("당첨 번호와 중복되는 값이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenNumberIsDuplicateWinningNumber() {
        //Given
        final String number = "1";
        final Lotto winningLotto = createLotto(List.of(1, 2, 3, 4, 5, 6));

        //When & Then
        assertThatThrownBy(() -> createBonusNumber(number, winningLotto))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 당첨 번호와 중복되는 숫자는 입력할 수 없어요.");
    }
}
