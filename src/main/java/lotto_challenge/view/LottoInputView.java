package lotto_challenge.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoInputView {

    private static final String NUMBER_SEPARATOR_PATTERN = ",";
    private static final String NUMBER_PATTERN = "0-9";

    public String inputStartOption() {
        System.out.println(LottoInputGuideMessage.INPUT_START_OPTION_GUIDE_MESSAGE.getMessage());
        System.out.println(LottoInputGuideMessage.FIRST_OPTION_MESSAGE.getMessage());
        System.out.println(LottoInputGuideMessage.SECOND_OPTION_MESSAGE.getMessage());
        System.out.println(LottoInputGuideMessage.THIRD_OPTION_MESSAGE.getMessage());
        System.out.println(LottoInputGuideMessage.INPUT_SEPARATOR.getMessage());

        final String option = Console.readLine();
        System.out.println();

        return option;
    }

    public String inputPurchasePrice() {
        System.out.println(LottoInputGuideMessage.INPUT_PURCHASE_PRICE_GUIDE_MESSAGE.getMessage());
        final String price = Console.readLine();
        System.out.println();

        return price;
    }

    public List<Integer> inputWinningLotto() {
        System.out.println(LottoInputGuideMessage.INPUT_WINNING_LOTTO_NUMBER_GUIDE_MESSAGE.getMessage());
        final String numbers = Console.readLine();
        System.out.println();

        validateWinningLottoNumberSeparator(numbers);

        final List<Integer> lottoNumbers = Arrays.stream(numbers.split(NUMBER_SEPARATOR_PATTERN))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        return lottoNumbers;
    }

    private void validateWinningLottoNumberSeparator(final String numbers) {
        String pattern = "^[" + NUMBER_PATTERN + "]+(" + NUMBER_SEPARATOR_PATTERN + "[" + NUMBER_PATTERN + "]+)*$";

        if(!numbers.matches(pattern)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 " + NUMBER_SEPARATOR_PATTERN + "를 기준으로 구분해야 해요.");
        }
    }

    public String inputWinningBonusNumber() {
        System.out.println(LottoInputGuideMessage.INPUT_WINNING_BONUS_NUMBER_GUIDE_MESSAGE.getMessage());
        final String numbers = Console.readLine();
        System.out.println();

        return numbers;
    }
}
