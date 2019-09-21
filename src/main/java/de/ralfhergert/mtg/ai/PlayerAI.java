package de.ralfhergert.mtg.ai;

import de.ralfhergert.mtg.model.Card;

import java.util.Collection;

public interface PlayerAI {

    ScryResult scry(Collection<Card> cards);
}
