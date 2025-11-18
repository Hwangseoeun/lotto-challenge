package lotto_challenge.core.service;

import lotto_challenge.core.repository.LottoStatisticRepository;
import lotto_challenge.core.service.dto.GetLottoStatisticDto;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;
import lotto_challenge.core.service.dto.SaveLottoStatisticDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LottoStatisticService {

    private final LottoStatisticRepository lottoStatisticRepository;

    public LottoStatisticService(final LottoStatisticRepository lottoStatisticRepository) {
        this.lottoStatisticRepository = lottoStatisticRepository;
    }

    public void saveLottoStatistic(final SaveLottoStatisticDto dto) {
        lottoStatisticRepository.save(dto.memberId(), dto.getPurchasePrice(), dto.getReturnRate());
    }

    @Transactional(readOnly = true)
    public List<LottoStatisticInfoDto> getLottoStatistics(final GetLottoStatisticDto dto) {
        return lottoStatisticRepository.findAllByMemberEmail(dto.email());
    }
}
