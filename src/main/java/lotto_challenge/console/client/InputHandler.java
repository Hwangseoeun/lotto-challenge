package lotto_challenge.console.client;

import lotto_challenge.console.view.LottoInputView;
import lotto_challenge.console.view.LottoOutputView;

import java.util.List;

public class InputHandler {

    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;

    public InputHandler(final LottoInputView lottoInputView, final LottoOutputView lottoOutputView) {
        this.lottoInputView = lottoInputView;
        this.lottoOutputView = lottoOutputView;
    }

    public StartOptionNumber getStartOptionNumber() {
        while(true) {
            try {
                final String startOption = lottoInputView.inputStartOption();
                return new StartOptionNumber(startOption);
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    public String getMemberEmail() {
        while(true) {
            try {
                return lottoInputView.inputMemberEmail();
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    public String getPurchasePrice() {
        while(true) {
            try {
                return lottoInputView.inputPurchasePrice();
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    public List<Integer> getWinningLotto() {
        while(true) {
            try {
                return lottoInputView.inputWinningLotto();
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }

    public String getBonusNumber() {
        while(true) {
            try {
                return lottoInputView.inputWinningBonusNumber();
            }
            catch (IllegalArgumentException e) {
                lottoOutputView.outputExceptionMessage(e);
            }
        }
    }
}
