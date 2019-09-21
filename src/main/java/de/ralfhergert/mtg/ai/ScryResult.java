package de.ralfhergert.mtg.ai;

import de.ralfhergert.mtg.model.Card;

import java.util.ArrayList;
import java.util.List;

public class ScryResult {

    List<Card> cardsOnTop = new ArrayList<>();
    List<Card> cardsOnBottom = new ArrayList<>();

    public List<Card> getCardsOnTop() {
        return cardsOnTop;
    }

    public List<Card> getCardsOnBottom() {
        return cardsOnBottom;
    }
}
