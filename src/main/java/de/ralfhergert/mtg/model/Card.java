package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Card implements Copyable<Card> {

    private static final Logger LOG = LoggerFactory.getLogger(Card.class);

    private final Reference<Card> reference;

    public Card() {
        this(new Reference<>(Card.class));
    }
    private Card(Reference<Card> reference) {
        this.reference = reference;
    }

    @Override
    public Card deepCopy() {
        final Card card = new Card(reference);
        return card;
    }

    public Reference<Card> getReference() {
        return reference;
    }
}
