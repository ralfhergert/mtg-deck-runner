package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.mtg.skill.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Card implements Copyable<Card>, Referenceable<Card> {

    public enum Type {
        Land
    }

    private static final Logger LOG = LoggerFactory.getLogger(Card.class);

    private final Reference<Card> reference;

    private List<Type> types = new ArrayList<>();
    private String name;
    private List<Skill> skills = new ArrayList<>();

    public Card() {
        this(new Reference<>(Card.class));
    }
    private Card(Reference<Card> reference) {
        this.reference = reference;
    }

    @Override
    public Card deepCopy() {
        final Card card = new Card(reference);
        card.types = new ArrayList<>(types);
        card.skills = new ArrayList<>(skills);
        return card;
    }

    public Reference<Card> getReference() {
        return reference;
    }

    public boolean isType(Type type) {
        return types.stream().anyMatch(t -> t == type);
    }

    public List<Type> getTypes() {
        return types;
    }

    public Card setTypes(Collection<Type> types) {
        this.types = new ArrayList<>(types);
        return this;
    }

    public String getName() {
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public Stream<Skill> getSkills(Predicate<Skill> predicate) {
        return skills.stream().filter(predicate);
    }

    public Card addSkill(Skill skill) {
        skills.add(skill);
        return this;
    }
}
