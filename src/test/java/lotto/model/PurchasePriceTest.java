package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchasePriceTest {

    private static PurchasePrice createPurchasePrice(final String price) {
        return new PurchasePrice(price);
    }

    @DisplayName("생성자에 유효한 입력값(금액 문자열)을 입력하면 PurchasePrice 인스턴스가 생성된다.")
    @Test
    void givenPrice_whenCreatePurchasePrice_thenSuccess() {
        //Given
        final String price = "8000";

        //When & Then
        assertThatCode(() -> createPurchasePrice(price))
            .doesNotThrowAnyException();
    }

    @DisplayName("숫자가 아닌 다른 값이 포함되어 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenPriceIsInvalid() {
        //Given
        final String price = "8000j";

        //When & Then
        assertThatThrownBy(() -> createPurchasePrice(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 구입 금액은 숫자로만 입력해야 해요.");
    }

    @DisplayName("올바르지 않은 단위의 금액이 들어오면 예외가 발생한다.")
    @Test
    void thrownException_whenNumberUnitIsInvalid() {
        //Given
        final String price = "1234";

        //When & Then
        assertThatThrownBy(() -> createPurchasePrice(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 구입 금액은 1000원 단위로만 가능해요.");
    }
}
