package lotto_challenge.core.model;

import lotto_challenge.core.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private static final List<Integer> WINNING_LOTTO_NUMBERS = List.of(6, 9, 16, 19, 24, 28);
    private static final int WINNING_BONUS_NUMBER = 17;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateNumberRange(numbers);
        validateNumberCount(numbers);

        this.numbers = sortNumbers(numbers);
    }

    private void validateNumberRange(final List<Integer> numbers) {
        for(int number : numbers) {
            if(number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 " + MIN_NUMBER +  " ~ " + MAX_NUMBER + " 사이의 숫자만 가능해요.");
            }
        }
    }

    private void validateNumberCount(final List<Integer> numbers) {
        final Set<Integer> setNumbers = new HashSet<>(numbers);

        if(setNumbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되면 안되며 번호는 " + NUMBER_COUNT + "개여야 해요.");
        }
    }

    private List<Integer> sortNumbers(final List<Integer> numbers) {
        final List<Integer> sortNumbers = new ArrayList<>(numbers);
        sortNumbers.sort(Integer::compareTo);

        return sortNumbers;
    }

    public static Lotto generate(final RandomNumberGenerator randomNumberGenerator) {
        final List<Integer> numbers = randomNumberGenerator.pickRandomLottoNumbers(
            RandomNumberGenerator.MIN_RANDOM_NUMBER,
            RandomNumberGenerator.MAX_RANDOM_NUMBER,
            RandomNumberGenerator.NUMBER_COUNT
        );

        return new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int countMatchedNumbers() {
        final List<Integer> copyNumbers = new ArrayList<>(numbers);
        copyNumbers.retainAll(WINNING_LOTTO_NUMBERS);

        return copyNumbers.size();
    }

    public boolean isMatchedBonusNumber() {
        return numbers.contains(WINNING_BONUS_NUMBER);
    }
}
