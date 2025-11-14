package lotto_challenge.core.model;

import java.util.List;

public class Lottos {

    private final List<Lotto> value;

    public Lottos(final List<Lotto> lottos) {
        this.value = lottos;
    }

    public List<Lotto> getValue() {
        return value;
    }
}
