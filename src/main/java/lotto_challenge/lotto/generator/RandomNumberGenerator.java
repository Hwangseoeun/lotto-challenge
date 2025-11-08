package lotto_challenge.lotto.generator;

import java.util.List;

public interface RandomNumberGenerator {

    int MIN_RANDOM_NUMBER = 1;
    int MAX_RANDOM_NUMBER = 45;
    int NUMBER_COUNT = 6;

    List<Integer> pickRandomLottoNumbers(final int minRandomNumber, final int maxRandomNumber, final int numberCount);
}
