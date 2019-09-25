package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Card implements Copyable<Card> {

    public enum Type {
        Land
    }

    private static final Logger LOG = LoggerFactory.getLogger(Card.class);

    private final Reference<Card> reference;

    private Type type;
    private String name;

    public Card() {
        this(new Reference<>(Card.class));
    }
    private Card(Reference<Card> reference) {
        this.reference = reference;
    }

    @Override
    public Card deepCopy() {
        final Card card = new Card(reference);
        card.type = type;
        return card;
    }

    public Reference<Card> getReference() {
        return reference;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
