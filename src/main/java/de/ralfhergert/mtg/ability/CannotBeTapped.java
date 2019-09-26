package de.ralfhergert.mtg.ability;

import de.ralfhergert.mtg.model.*;

public class CannotBeTapped extends Ability<CannotBeTapped> {

    public CannotBeTapped() {
        this(new Reference<>(CannotBeTapped.class));
    }

    private CannotBeTapped(final Reference<CannotBeTapped> reference) {
        super(reference);
    }

    @Override
    public CannotBeTapped deepCopy() {
        return new CannotBeTapped(getReference());
    }

    @Override
    public boolean areRequirementsMet(Game game) {
        return true;
    }

    @Override
    public Game apply(Game game) {
        return game;
    }
}
