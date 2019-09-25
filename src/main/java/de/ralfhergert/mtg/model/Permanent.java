package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.cloning.CopyableList;

public class Permanent implements Copyable<Permanent> {

    public enum Type {
        Land
    }

    private final Reference<Permanent> reference;

    private String name;
    private Type type;
    private CopyableList<Ability> abilities = new CopyableList<>();

    public Permanent() {
        this(new Reference<>(Permanent.class));
    }

    private Permanent(final Reference<Permanent> reference) {
        this.reference = reference;
    }

    @Override
    public Permanent deepCopy() {
        Permanent permanent = new Permanent(reference);
        permanent.name = name;
        permanent.type = type;
        permanent.abilities = abilities.deepCopy();
        return null;
    }
}
