package lotto_challenge.core.repository.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;

@Table(name = "lotto_statistic")
@Entity
public class LottoStatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private MemberEntity member;

    @Column(name = "purchase_price", nullable = false)
    private int purchasePrice;

    @Column(name = "return_rate", nullable = false)
    private float returnRate;

    public LottoStatisticEntity() {}

    public LottoStatisticEntity(final MemberEntity member, final PurchasePrice purchasePrice, final ReturnRate returnRate) {
        this.member = member;
        this.purchasePrice = purchasePrice.getValue();
        this.returnRate = returnRate.getValue();
    }
}
