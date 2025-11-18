package lotto_challenge.core.repository.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lotto_challenge.core.model.Member;

@Table(name = "member")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    public MemberEntity() {}

    public MemberEntity(final Member member) {
        this.email = member.getEmail();
    }

    public Member toMember() {
        return new Member(email);
    }
}
