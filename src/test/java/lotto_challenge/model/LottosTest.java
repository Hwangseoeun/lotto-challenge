package lotto_challenge.model;

import lotto_challenge.lotto.model.Lotto;
import lotto_challenge.lotto.model.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class LottosTest {

    private static Lottos createLottos(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    private static Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    @DisplayName("생성자에 유효한 입력값(로또 리스트)을 입력하면 Lottos 인스턴스가 생성된다.")
    @Test
    void givenLottoList_whenCreateLottos_thenSuccess() {
        //Given
        final List<Lotto> lottos = Arrays.asList(
            createLotto(List.of(1, 2, 3, 4, 5, 6)),
            createLotto(List.of(1, 2, 3, 4, 5, 6)),
            createLotto(List.of(1, 2, 3, 4, 5, 6))
        );

        //When & Then
        assertThatCode(() -> createLottos(lottos))
            .doesNotThrowAnyException();
    }
}
