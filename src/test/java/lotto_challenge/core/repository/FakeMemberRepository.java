package lotto_challenge.core.repository;

import lotto_challenge.core.model.Member;

import java.util.HashMap;
import java.util.Map;

public class FakeMemberRepository implements MemberRepository {

    private final Map<Long, Member> data = new HashMap<>();

    @Override
    public Long save(final Member member) {
        final Long id = data.size()+1L;

        if (!data.containsValue(member)) {
            data.put(id, member);
        }

        return id;
    }

    @Override
    public boolean existsByEmail(final String email) {
        for(Map.Entry<Long, Member> entry : data.entrySet()) {
            if(entry.getValue().getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Long findIdByEmail(final String email) {
        for(Map.Entry<Long, Member> entry : data.entrySet()) {
            if(entry.getValue().getEmail().equals(email)) {
                return entry.getKey();
            }
        }

        return null;
    }
}
