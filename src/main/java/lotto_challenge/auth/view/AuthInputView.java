package lotto_challenge.auth.view;

import camp.nextstep.edu.missionutils.Console;

public class AuthInputView {

    public String inputStartOption() {
        System.out.println(AuthInputGuideMessage.INPUT_START_OPTION_GUIDE_MESSAGE.getMessage());
        for(StartOption startOption : StartOption.values()) {
            System.out.println(startOption.getOptionNumber() + ". " + startOption.getDescription());
        }
        System.out.println(AuthInputGuideMessage.INPUT_SEPARATOR.getMessage());

        final String chooseOption = Console.readLine();

        System.out.println();

        return chooseOption;
    }

    public String inputSignUp() {
        System.out.print(AuthInputGuideMessage.INPUT_SIGN_UP_GUIDE_MESSAGE.getMessage());
        final String userEmail = Console.readLine();
        System.out.println();

        return userEmail;
    }

    public String inputLogin() {
        System.out.print(AuthInputGuideMessage.INPUT_LOGIN_GUIDE_MESSAGE.getMessage());
        final String userEmail = Console.readLine();
        System.out.println();

        return userEmail;
    }
}
