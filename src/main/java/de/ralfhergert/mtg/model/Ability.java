package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;

public abstract class Ability<T extends Ability<T>> implements Copyable<T>, Referenceable<T> {

    private final Reference<T> reference;

    protected Ability(final Reference<T> reference) {
        this.reference = reference;
    }

    public Reference<T> getReference() {
        return reference;
    }

    public abstract boolean areRequirementsMet(Game game);

    public abstract Game apply(Game game);
}
