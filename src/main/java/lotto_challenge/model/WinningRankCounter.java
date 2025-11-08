package lotto_challenge.model;

import java.util.EnumMap;
import java.util.Map;

public class WinningRankCounter {

    private static final int DEFAULT_COUNT = 0;
    private static final int INCREASE_UNIT = 1;

    private final Map<Rank, Integer> result = new EnumMap<>(Rank.class);

    public WinningRankCounter() {
        for(Rank rank : Rank.values()) {
            result.put(rank, DEFAULT_COUNT);
        }
    }

    public void increase(final Rank rank) {
        result.put(rank, result.get(rank) + INCREASE_UNIT);
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public int calculateReturn() {
        int totalReturn = 0;

        for(Rank rank : Rank.values()) {
            totalReturn += rank.getPrizeMoney() * result.get(rank);
        }

        return totalReturn;
    }
}
