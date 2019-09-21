package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Card implements Copyable<Card> {

    private static final Logger LOG = LoggerFactory.getLogger(Card.class);

    @Override
    public Card deepCopy() {
        final Card card = new Card();
        return card;
    }
}
