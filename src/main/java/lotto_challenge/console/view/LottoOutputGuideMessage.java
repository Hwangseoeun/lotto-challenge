package lotto_challenge.console.view;

public enum LottoOutputGuideMessage {

    OUTPUT_SERVER_EXCEPTION_GUIDE_MESSAGE("[ERROR] 서버와 통신할 수 없습니다."),
    OUTPUT_LOTTO_QUANTITY_GUIDE_MESSAGE("개를 구매했습니다."),
    OUTPUT_WINNING_RESULT_GUIDE_MESSAGE("당첨 통계\n---"),
    OUTPUT_WINNING_RESULT_SECOND_PRIZE_GUIDE_MESSAGE("%d개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    OUTPUT_WINNING_RESULT_OTHER_PRIZE_GUIDE_MESSAGE("%d개 일치 (%,d원) - %d개"),
    OUTPUT_RETURN_RATE_GUIDE_MESSAGE("총 수익률은 %.1f%c입니다."),
    OUTPUT_LOTTO_STATISTIC_GUIDE_MESSAGE("그동안의 당첨 통계 내역"),
    OUTPUT_SEPARATOR("------------------"),
    OUTPUT_INVALID_MEMBER_GUIDE_MESSAGE("[ERROR] 존재하지 않는 회원이에요."),
    ;

    private final String message;

    LottoOutputGuideMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
