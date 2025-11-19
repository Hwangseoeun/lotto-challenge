package lotto_challenge.core.repository.dto;

public record LottoStatisticEntry(

    Long memberId,
    int purchasePrice,
    float returnRate

) {
}
