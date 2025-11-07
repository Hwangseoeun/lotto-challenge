package lotto.model;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {

    NONE(0, 0, (matchedCount, isBonusMatched) -> matchedCount == 0),
    FIFTH_PRIZE(3, 5000, (matchedCount, isBonusMatched) -> matchedCount == 3),
    FOURTH_PRIZE(4, 50000, (matchedCount, isBonusMatched) -> matchedCount == 4),
    THIRD_PRIZE(5, 1500000, (matchedCount, isBonusMatched) -> matchedCount == 5 && !isBonusMatched),
    SECOND_PRIZE(5, 30000000, (matchedCount, isBonusMatched) -> matchedCount == 5 && isBonusMatched),
    FIRST_PRIZE(6, 2000000000, (matchedCount, isBonusMatched) -> matchedCount == 6),
    ;

    private final int matchedNumberCount;
    private final int prizeMoney;
    private final BiFunction<Integer, Boolean, Boolean> isMatchedCondition;

    Rank(final int matchedNumberCount, final int prizeMoney, final BiFunction<Integer, Boolean, Boolean> isMatchedCondition) {
        this.matchedNumberCount = matchedNumberCount;
        this.prizeMoney = prizeMoney;
        this.isMatchedCondition = isMatchedCondition;
    }

    public int getMatchedNumberCount() {
        return matchedNumberCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public BiFunction<Integer, Boolean, Boolean> getIsMatchedCondition() {
        return isMatchedCondition;
    }

    public static Rank of(final int matchedCount, final boolean isBonusMatched) {
        return Arrays.stream(values())
            .filter(rank -> rank.isMatchedCondition.apply(matchedCount, isBonusMatched))
            .findFirst()
            .orElse(NONE);
    }
}
