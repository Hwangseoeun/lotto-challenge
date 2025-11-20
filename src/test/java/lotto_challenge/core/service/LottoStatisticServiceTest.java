package lotto_challenge.core.service;

import lotto_challenge.console.config.FakeAppConfig;
import lotto_challenge.core.model.Member;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.repository.FakeLottoStatisticRepository;
import lotto_challenge.core.repository.FakeMemberRepository;
import lotto_challenge.core.service.dto.GetLottoStatisticDto;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;
import lotto_challenge.core.service.dto.SaveLottoStatisticDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoStatisticServiceTest {

    private static LottoStatisticService lottoStatisticService;
    private static FakeMemberRepository fakeMemberRepository;
    private static FakeLottoStatisticRepository fakeLottoStatisticRepository;

    @BeforeEach
    void setUp() {
        FakeAppConfig config = new FakeAppConfig();
        fakeMemberRepository = config.createFakeMemberRepository();
        fakeLottoStatisticRepository = config.createFakeLottoStatisticRepository(fakeMemberRepository);
        lottoStatisticService = config.createLottoStatisticService(fakeLottoStatisticRepository);
    }

    private static LottoStatisticService createLottoStatisticService() {
        return new LottoStatisticService(fakeLottoStatisticRepository);
    }

    @DisplayName("생성자에 유효한 입력값(lottoStatistic repository)을 입력하면 LottoStatisticService 인스턴스가 생성된다.")
    @Test
    void givenLottoStatisticRepository_whenCreateLottoStatisticService_thenSuccess() {
        //When & Then
        assertThatCode(() -> createLottoStatisticService())
            .doesNotThrowAnyException();
    }

    @DisplayName("유효한 dto(memberId, purchasePrice, returnRate)를 입력하면 값을 저장한다.")
    @Test
    void givenSaveLottoStatisticDto_whenSaveLottoStatistic_thenSaveSuccess() {
        // Given
        final Member member = new Member("test@gmail.com");
        final Long memberId = fakeMemberRepository.save(member);

        final PurchasePrice purchasePrice = new PurchasePrice("8000");
        final ReturnRate returnRate = new ReturnRate(5000, purchasePrice);

        final SaveLottoStatisticDto dto = new SaveLottoStatisticDto(
            memberId,
            purchasePrice,
            returnRate
        );

        // When
        lottoStatisticService.saveLottoStatistic(dto);

        // Then
        List<LottoStatisticInfoDto> result = fakeLottoStatisticRepository.findAllByMemberEmail(member.getEmail());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPurchasePrice()).isEqualTo(8000);
        assertThat(result.get(0).getReturnRate()).isEqualTo(returnRate.getValue());
    }

    @DisplayName("유효한 dto(email)를 입력하면 값을 모두 조회한다.")
    @Test
    void givenGetLottoStatisticDto_whenGetLottoStatistics_thenReturnAllLottoStatistics() {
        //Given
        final Member member = new Member("test@gmail.com");
        final Long memberId = fakeMemberRepository.save(member);

        final PurchasePrice purchasePrice = new PurchasePrice("8000");
        final ReturnRate returnRate = new ReturnRate(5000, purchasePrice);

        final SaveLottoStatisticDto saveLottoStatisticDto = new SaveLottoStatisticDto(
            memberId,
            purchasePrice,
            returnRate
        );

        lottoStatisticService.saveLottoStatistic(saveLottoStatisticDto);

        final GetLottoStatisticDto dto = new GetLottoStatisticDto("test@gmail.com");

        //When
        final List<LottoStatisticInfoDto> lottoStatistics = lottoStatisticService.getLottoStatistics(dto);

        //Then
        assertThat(lottoStatistics).hasSize(1);
    }
}