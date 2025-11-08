package lotto_challenge.auth.controller;

import lotto_challenge.auth.model.Member;
import lotto_challenge.auth.model.StartOptionNumber;
import lotto_challenge.auth.view.AuthInputView;
import lotto_challenge.auth.view.AuthOutputView;

import static lotto_challenge.auth.view.StartOption.EXIT;
import static lotto_challenge.auth.view.StartOption.LOGIN;
import static lotto_challenge.auth.view.StartOption.SIGN_UP;

public class AuthController {

    private final AuthInputView authInputView;
    private final AuthOutputView authOutputView;

    public AuthController(final AuthInputView authInputView, final AuthOutputView authOutputView) {
        this.authInputView = authInputView;
        this.authOutputView = authOutputView;
    }

    public void startAuth() {
        final StartOptionNumber startOptionNumber = getStartOptionNumber();

        Member member;

        if(startOptionNumber.getValue() == SIGN_UP.getOptionNumber()) {
            member = getMemberEmailToSignUp();
        }

        if(startOptionNumber.getValue() == LOGIN.getOptionNumber()) {
            member = getMemberEmailToLogin();
        }

        if (startOptionNumber.getValue() == EXIT.getOptionNumber()) {
            System.exit(0);
        }
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

    private Member getMemberEmailToSignUp() {
        while(true) {
            try {
                final String userEmail = authInputView.inputSignUp();
                return new Member(userEmail);
            }
            catch (IllegalArgumentException e) {
                authOutputView.outputExceptionMessage(e);
            }
        }
    }

    private Member getMemberEmailToLogin() {
        while(true) {
            try {
                final String userEmail = authInputView.inputLogin();
                return new Member(userEmail);   // 추후 new가 아닌 DB에서 조회해오도록 수정 필요
            }
            catch (IllegalArgumentException e) {
                authOutputView.outputExceptionMessage(e);
            }
        }
    }
}
