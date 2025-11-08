package lotto_challenge.lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomLottoNumbersGenerator implements RandomNumberGenerator {

    @Override
    public List<Integer> pickRandomLottoNumbers(final int minRandomNumber, final int maxRandomNumber, final int numberCount) {
        return Randoms.pickUniqueNumbersInRange(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER, NUMBER_COUNT);
    }
}
