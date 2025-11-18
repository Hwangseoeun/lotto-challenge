package lotto_challenge.console.view;

import camp.nextstep.edu.missionutils.Console;

public class LottoInputView {

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

    public String inputMemberEmail() {
        System.out.println(LottoInputGuideMessage.INPUT_MEMBER_EMAIL_GUIDE_MESSAGE.getMessage());
        final String memberEmail = Console.readLine();
        System.out.println();

        return memberEmail;
    }

    public String inputPurchasePrice() {
        System.out.println(LottoInputGuideMessage.INPUT_PURCHASE_PRICE_GUIDE_MESSAGE.getMessage());
        final String price = Console.readLine();
        System.out.println();

        return price;
    }
}
