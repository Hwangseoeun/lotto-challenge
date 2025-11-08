package lotto_challenge.view;

public enum LottoOutputGuideMessage {

    OUTPUT_LOTTO_QUANTITY_GUIDE_MESSAGE("개를 구매했습니다."),
    OUTPUT_WINNING_RESULT_GUIDE_MESSAGE("당첨 통계\n---"),
    OUTPUT_WINNING_RESULT_SECOND_PRIZE_GUIDE_MESSAGE("%d개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    OUTPUT_WINNING_RESULT_OTHER_PRIZE_GUIDE_MESSAGE("%d개 일치 (%,d원) - %d개"),
    OUTPUT_RETURN_RATE_GUIDE_MESSAGE("총 수익률은 %.1f%c입니다."),
    ;

    private final String message;

    LottoOutputGuideMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
