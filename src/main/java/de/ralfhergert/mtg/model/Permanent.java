package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.cloning.CopyableList;

import java.util.function.Predicate;

public class Permanent implements Copyable<Permanent>, Referenceable<Permanent> {

    public enum Type {
        Land
    }

    private final Reference<Permanent> reference;

    private String name;
    private Type type;
    private CopyableList<? extends Ability> abilities = new CopyableList<>();
    private boolean isTapped = false;

    public Permanent() {
        this(new Reference<>(Permanent.class));
    }

    private Permanent(final Reference<Permanent> reference) {
        this.reference = reference;
    }

    @Override
    public Reference<Permanent> getReference() {
        return reference;
    }

    @Override
    public Permanent deepCopy() {
        Permanent clone = new Permanent(reference);
        clone.name = name;
        clone.type = type;
        clone.abilities = abilities.deepCopy();
        clone.isTapped = isTapped;
        return clone;
    }

    public boolean hasAbility(Predicate<Ability> abilityPredicate) {
        return abilities.stream().anyMatch(abilityPredicate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isTapped() {
        return isTapped;
    }

    public void setTapped(boolean tapped) {
        isTapped = tapped;
    }
}
