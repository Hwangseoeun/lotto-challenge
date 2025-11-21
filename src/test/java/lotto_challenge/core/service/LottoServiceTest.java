package lotto_challenge.core.service;

import lotto_challenge.console.config.FakeAppConfig;
import lotto_challenge.core.model.Lotto;
import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.Rank;
import lotto_challenge.core.model.WinningRankCounter;
import lotto_challenge.core.service.dto.GenerateLottoDto;
import lotto_challenge.core.service.dto.JudgeRankDto;
import lotto_challenge.core.service.dto.LottosDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private final LottoService lottoService = FakeAppConfig.getLottoService();

    @DisplayName("유효한 dto(purchasePrice)가 들어오면 로또들을 발급한 후 LottosDetailDto(price, lottoQuantity, savedLottos)를 반환한다.")
    @Test
    void givenGenerateLottoDto_whenGenerateLottos_thenReturnLottosDetailDto() {
        //Given
        final String price = "8000";
        final GenerateLottoDto generateLottoDto = new GenerateLottoDto(price);

        final PurchasePrice purchasePrice = new PurchasePrice(price);
        final LottoQuantity lottoQuantity = new LottoQuantity(purchasePrice);

        //When
        final LottosDetailDto result = lottoService.generateLottos(generateLottoDto);

        //Then
        assertThat(result.purchasePrice().getValue()).isEqualTo(Integer.parseInt(price));
        assertThat(result.lottoQuantity().getValue()).isEqualTo(lottoQuantity.getValue());
        assertThat(result.lottos().getValue().size()).isEqualTo(8);
    }

    @DisplayName("유효한 dto(JudgeRankDto)가 들어오면 각 로또들의 당첨 순위를 결정한다.")
    @Test
    void givenJudgeRankDto_whenJudgeRank_thenIncreaseWinningRankCounter() {
        //Given
        final Lottos lottos = new Lottos(
            List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
            )
        );

        final WinningRankCounter winningRankCounter = new WinningRankCounter();

        final JudgeRankDto judgeRankDto = new JudgeRankDto(lottos, winningRankCounter);

        //When
        lottoService.judgeRank(judgeRankDto);

        //Then
        final Map<Rank, Integer> result = winningRankCounter.getResult();

        assertThat(result.get(Rank.NONE)).isEqualTo(1);
    }
}