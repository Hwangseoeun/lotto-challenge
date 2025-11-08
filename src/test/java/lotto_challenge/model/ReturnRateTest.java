package lotto_challenge.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ReturnRateTest {

    private static ReturnRate createReturnRate(final int totalReturn, final PurchasePrice purchasePrice) {
        return new ReturnRate(totalReturn, purchasePrice);
    }

    private static PurchasePrice createPurchasePrice(final String price) {
        return new PurchasePrice(price);
    }

    @DisplayName("생성자에 유효한 입력값(총 수익 금액과 구입 금액)을 입력하면 ReturnRate 인스턴스가 생성된다.")
    @Test
    void givenTotalReturnAndPurchasePrice_whenCreateReturnRate_thenSuccess() {
        //Given
        final int totalReturn = 5000;
        final PurchasePrice purchasePrice = createPurchasePrice("8000");

        //When & Then
        assertThatCode(() -> createReturnRate(totalReturn, purchasePrice))
            .doesNotThrowAnyException();
    }

    @DisplayName("총 수익을 구입 금액으로 나눈 값 * 100의 수익률이 ReturnRate 인스턴스에 저장된다.")
    @Test
    void givenTotalReturnAndPurchasePrice_whenCalculateReturnRate_thenSaveRate() {
        //Given
        final int totalReturn = 5000;
        final PurchasePrice purchasePrice = createPurchasePrice("8000");
        final float expected = (float) 62.5;

        //When
        final ReturnRate returnRate = createReturnRate(totalReturn, purchasePrice);

        //Then
        assertThat(returnRate.getValue()).isEqualTo(expected);
    }
}