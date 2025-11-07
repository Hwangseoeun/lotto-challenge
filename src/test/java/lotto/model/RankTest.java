package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static lotto.model.Rank.FIFTH_PRIZE;
import static lotto.model.Rank.FIRST_PRIZE;
import static lotto.model.Rank.FOURTH_PRIZE;
import static lotto.model.Rank.NONE;
import static lotto.model.Rank.SECOND_PRIZE;
import static lotto.model.Rank.THIRD_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RankTest {

    @DisplayName("일치하는 번호 개수와 보너스 번호의 일치 여부에 따라 상금을 결정한다.")
    @MethodSource
    @ParameterizedTest
    void givenMatchedCountAndIsBonusMatched_whenRank_thenReturnRank(
        final BiFunction<Integer, Boolean, Boolean> isMatchedCondition,
        final int matchedCount,
        final boolean isBonusMatched
    ) {
        //When & Then
        assertThat(isMatchedCondition.apply(matchedCount, isBonusMatched)).isTrue();
    }

    private static Stream<Arguments> givenMatchedCountAndIsBonusMatched_whenRank_thenReturnRank() {
        return Stream.of(
            arguments(FIRST_PRIZE.getIsMatchedCondition(), 6, false),
            arguments(SECOND_PRIZE.getIsMatchedCondition(), 5, true),
            arguments(THIRD_PRIZE.getIsMatchedCondition(), 5, false),
            arguments(FOURTH_PRIZE.getIsMatchedCondition(), 4, false),
            arguments(FIFTH_PRIZE.getIsMatchedCondition(), 3, false),
            arguments(NONE.getIsMatchedCondition(), 0, false)
        );
    }
}
