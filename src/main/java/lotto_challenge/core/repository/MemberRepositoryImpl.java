package lotto_challenge.core.repository;

import lotto_challenge.core.model.Member;
import lotto_challenge.core.repository.jpa.JpaMemberRepository;
import lotto_challenge.core.repository.jpa.entity.MemberEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    public MemberRepositoryImpl(JpaMemberRepository jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    @Override
    public Long save(final Member member) {
        final MemberEntity memberEntity = jpaMemberRepository.save(new MemberEntity(member));
        return memberEntity.getId();
    }

    @Override
    public boolean existsByEmail(final String email) {
        return jpaMemberRepository.existsByEmail(email);
    }

    @Override
    public Long findIdByEmail(final String email) {
        return jpaMemberRepository.findIdByEmail(email);
    }
}
