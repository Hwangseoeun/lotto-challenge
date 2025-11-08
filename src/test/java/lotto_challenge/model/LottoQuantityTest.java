package lotto_challenge.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoQuantityTest {

    private static LottoQuantity createLottoQuantity(final PurchasePrice purchasePrice) {
        return new LottoQuantity(purchasePrice);
    }

    private static PurchasePrice createPurchasePrice(final String price) {
        return new PurchasePrice(price);
    }

    @DisplayName("생성자에 유효한 입력값(구입 금액 인스턴스)을 입력하면 LottoQuantity 인스턴스가 생성된다.")
    @Test
    void givenPurchasePrice_whenCreateLottoQuantity_thenSuccess() {
        //Given
        final PurchasePrice purchasePrice = createPurchasePrice("8000");

        //When & Then
        assertThatCode(() -> createLottoQuantity(purchasePrice))
            .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액을 로또 금액으로 나눈 값이 LottoQuantity 인스턴스에 저장된다.")
    @Test
    void givenPurchasePrice_whenCreateLottoQuantity_thenSaveQuotient() {
        //Given
        final PurchasePrice purchasePrice = createPurchasePrice("8000");
        final int expectedQuantity = 8;

        //When
        final LottoQuantity lottoQuantity = createLottoQuantity(purchasePrice);

        //Then
        assertThat(lottoQuantity.getValue()).isEqualTo(expectedQuantity);
    }
}
