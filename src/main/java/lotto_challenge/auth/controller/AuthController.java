package lotto_challenge.auth.controller;

import lotto_challenge.auth.model.StartOptionNumber;
import lotto_challenge.auth.view.AuthInputView;
import lotto_challenge.auth.view.AuthOutputView;

public class AuthController {

    private final AuthInputView authInputView;
    private final AuthOutputView authOutputView;

    public AuthController(final AuthInputView authInputView, final AuthOutputView authOutputView) {
        this.authInputView = authInputView;
        this.authOutputView = authOutputView;
    }

    public void startAuth() {
        final StartOptionNumber startOptionNumber = getStartOptionNumber();
    }

    private StartOptionNumber getStartOptionNumber() {
        while(true) {
            try {
                final String chooseOption = authInputView.inputStartOption();
                return new StartOptionNumber(chooseOption);
            }
            catch (IllegalArgumentException e) {
                authOutputView.outputExceptionMessage(e);
            }
        }
    }
}
