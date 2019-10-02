package de.ralfhergert.mtg.card;

import de.ralfhergert.mtg.model.Card;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private final static Map<String, Card> cards = new HashMap<>();

    public Repository() {
        cards.put("SLW", new Card());
    }
}
