package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.cloning.CopyableList;

import java.util.ArrayList;
import java.util.List;
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

    private Card createdFromCard;

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
        clone.createdFromCard = createdFromCard.deepCopy();
        return clone;
    }

    public boolean hasAbility(Predicate<Ability> abilityPredicate) {
        return abilities.stream().anyMatch(abilityPredicate);
    }

    public String getName() {
        return name;
    }

    public Permanent setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Permanent setType(Type type) {
        this.type = type;
        return this;
    }

    public boolean isTapped() {
        return isTapped;
    }

    public Permanent setTapped(boolean tapped) {
        isTapped = tapped;
        return this;
    }

    public Permanent addAbility(Ability ability) {
        List<Ability> abilitiesList = new ArrayList<>(abilities);
        abilitiesList.add(ability);
        abilities = new CopyableList<>(abilitiesList);
        return this;
    }

    public Card getCreatedFromCard() {
        return createdFromCard;
    }

    public Permanent setCreatedFromCard(Card createdFromCard) {
        this.createdFromCard = createdFromCard;
        return this;
    }
}
