package lotto_challenge.model;

import lotto_challenge.core.model.BonusNumber;
import lotto_challenge.core.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    private static Lotto createLotto(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private static BonusNumber createBonusNumber(final String number, final Lotto buyingLotto) {
        return new BonusNumber(number, buyingLotto);
    }

    @DisplayName("생성자에 유효한 입력값(로또 번호 리스트)을 입력하면 Lotto 인스턴스가 생성된다.")
    @Test
    void givenLottoNumbers_whenCreateLotto_thenSuccess() {
        //Given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //When & Then
        assertThatCode(() -> createLotto(numbers))
            .doesNotThrowAnyException();
    }

    @DisplayName("1 ~ 45 사이의 숫자가 아닌 값이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenNumberIsLimitOver() {
        //Given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 60);

        //When & Then
        assertThatThrownBy(() -> createLotto(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 로또 번호는 1 ~ 45 사이의 숫자만 가능해요.");
    }

    @DisplayName("로또 번호가 중복되거나 개수가 6개가 아니면 예외가 발생한다.")
    @MethodSource
    @ParameterizedTest
    void thrownException_whenNumberIsDuplicateOrCountIsOver(final List<Integer> numbers) {
        //When & Then
        assertThatThrownBy(() -> createLotto(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 로또 번호는 중복되면 안되며 번호는 6개여야 해요.");
    }

    private static Stream<List<Integer>> thrownException_whenNumberIsDuplicateOrCountIsOver() {
        return Stream.of(
            Arrays.asList(1, 1, 3, 4, 5, 6),
            Arrays.asList(1, 2, 3, 4, 5)
        );
    }

    @DisplayName("로또 번호는 오름차순으로 정렬된다.")
    @Test
    void givenLottoNumbers_whenCreateLotto_thenSortedNumbers() {
        //Given
        final List<Integer> numbers = Arrays.asList(6, 5, 4, 3, 2, 1);
        final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);

        //When
        final Lotto lotto = createLotto(numbers);

        //Then
        assertThat(lotto.getNumbers())
            .containsExactlyElementsOf(expected);
    }

    @DisplayName("특정 값으로 Lotto 인스턴스를 생성한다.")
    @Test
    void whenGenerateLotto_thenCreatedLotto() {
        //Given
        final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);

        //When
        final Lotto result = Lotto.generate((minRandomNumber, maxRandomNumber, numberCount) -> List.of(1, 2, 3, 4, 5, 6));

        //Then
        assertThat(result.getNumbers())
            .containsExactlyElementsOf(expected);
    }

    @DisplayName("두 로또의 중복되는 값의 개수를 반환한다.")
    @Test
    void givenWinningLotto_whenCountMatchedNumbers_thenReturnCount() {
        //Given
        final Lotto buyingLotto = createLotto(Arrays.asList(1, 2, 3, 14, 15, 16));
        final Lotto winningLotto = createLotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        //When
        final int matchedNumberCount = buyingLotto.countMatchedNumbers(winningLotto);

        //Then
        assertThat(matchedNumberCount).isEqualTo(3);
    }

    @DisplayName("로또에 보너스 번호가 있는지 여부를 반환한다.")
    @Test
    void givenBonusNumber_whenIsMatchedBonusNumber_thenReturnWhether() {
        //Given
        final Lotto winningLotto = createLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Lotto buyingLotto = createLotto(Arrays.asList(31, 32, 33, 34, 35, 36));
        final BonusNumber bonusNumber = createBonusNumber("31", winningLotto);

        //When
        final boolean whether = buyingLotto.isMatchedBonusNumber(bonusNumber);

        //Then
        assertThat(whether).isTrue();
    }
}
