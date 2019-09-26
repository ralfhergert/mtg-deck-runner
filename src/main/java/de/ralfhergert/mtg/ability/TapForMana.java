package de.ralfhergert.mtg.ability;

import de.ralfhergert.mtg.model.*;

public class TapForMana extends Ability<TapForMana> {

    private int amount;
    private ManaType manaType;

    public TapForMana() {
        this(new Reference<>(TapForMana.class));
    }

    private TapForMana(final Reference<TapForMana> reference) {
        super(reference);
    }

    @Override
    public TapForMana deepCopy() {
        TapForMana clone = new TapForMana(getReference());
        clone.amount = amount;
        clone.manaType = manaType;
        return clone;
    }

    @Override
    public boolean areRequirementsMet(Game game) {
        // find the owner permanent
        final Permanent owner = game.getAllPermanents()
            .filter(permanent -> permanent.hasAbility(ability -> ability.getReference().equals(getReference())))
            .findAny()
            .orElseThrow(() -> new AssertionError("could not find owner permanent of ability"));

        return !owner.isTapped() && !owner.hasAbility(ability -> ability instanceof CannotBeTapped);
    }

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();

        final Permanent owner = clone.getAllPermanents()
            .filter(permanent -> permanent.hasAbility(ability -> ability.getReference().equals(getReference())))
            .findAny()
            .orElseThrow(() -> new AssertionError("could not find owner permanent of ability"));

        owner.setTapped(true);

        final Player player = game.findPlayer(p -> p.getBattleField().stream().anyMatch(owner.getReference().asPredicate()));

        player.getManaPool().add(amount, manaType);

        return clone;
    }
}
