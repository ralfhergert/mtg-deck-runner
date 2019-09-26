package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;

import java.util.HashMap;
import java.util.Map;

public class ManaPool implements Copyable<ManaPool> {

    private final Map<ManaType,Integer> pool = new HashMap<>();

    @Override
    public ManaPool deepCopy() {
        final ManaPool clone = new ManaPool();
        pool.forEach((key, value) -> clone.add(value, key));
        return clone;
    }

    public ManaPool add(final int amount, final ManaType type) {
        pool.merge(type, amount, Integer::sum);
        return this;
    }
}
