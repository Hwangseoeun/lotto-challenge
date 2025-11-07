package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class WinningRankCounterTest {

    private static WinningRankCounter createWinningRankCounter() {
        return new WinningRankCounter();
    }

    @DisplayName("생성자에서 Rank enum을 가지고 WinningRankCounter 인스턴스를 생성한다.")
    @Test
    void givenRankEnum_whenCreateWinningRankCounter_thenSuccess() {
        //When & Then
        assertThatCode(() -> createWinningRankCounter())
            .doesNotThrowAnyException();
    }

    @DisplayName("등수별 당첨된 로또의 개수를 증가한다.")
    @Test
    void givenRank_whenIncreaseCount_thenSuccess() {
        //Given
        final Rank rank = Rank.FIFTH_PRIZE;
        final WinningRankCounter winningRankCounter = createWinningRankCounter();
        final int expected = 1;

        //When
        winningRankCounter.increase(rank);
        final Integer count = winningRankCounter.getResult().get(rank);

        //Then
        assertThat(count).isEqualTo(expected);
    }

    @DisplayName("각각의 당첨된 로또 등수의 상금만큼 합하여 총 수익을 반환한다.")
    @Test
    void whenCalculatorReturn_thenReturn() {
        //Given
        final WinningRankCounter winningRankCounter = createWinningRankCounter();
        winningRankCounter.increase(Rank.FIFTH_PRIZE);
        final int expected = 5000;

        //When
        final int result = winningRankCounter.calculateReturn();

        //Then
        assertThat(result).isEqualTo(expected);
    }
}
