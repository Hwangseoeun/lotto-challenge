package lotto_challenge.core.repository;

import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;
import lotto_challenge.core.repository.dto.LottoStatisticEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeLottoStatisticRepository implements LottoStatisticRepository {

    private final Map<Long, LottoStatisticEntry> data = new HashMap<>();
    private final FakeMemberRepository memberRepository;

    public FakeLottoStatisticRepository(FakeMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void save(final Long memberId, final PurchasePrice purchasePrice, final ReturnRate returnRate) {
        final Long id = data.size()+1L;

        final LottoStatisticEntry entry = new LottoStatisticEntry(
            memberId,
            purchasePrice.getValue(),
            returnRate.getValue()
        );

        data.put(id, entry);
    }

    @Override
    public List<LottoStatisticInfoDto> findAllByMemberEmail(final String email) {
        final Long memberId = memberRepository.findIdByEmail(email);

        List<LottoStatisticInfoDto> result = new ArrayList<>();

        for(LottoStatisticEntry entry : data.values()) {
            if(entry.memberId().equals(memberId)) {
                final LottoStatisticInfoDto dto = new LottoStatisticInfoDto(
                    entry.purchasePrice(),
                    entry.returnRate()
                );

                result.add(dto);
            }
        }

        return result;
    }
}
