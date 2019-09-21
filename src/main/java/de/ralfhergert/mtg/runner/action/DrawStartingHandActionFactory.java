package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This factory lets player draw cards if they have not yet their opening hand
 * and less cards then their supposed startingHandSize.
 */
public class DrawStartingHandActionFactory implements ActionFactory<DrawCardsAction> {

    @Override
    public Collection<DrawCardsAction> getPossibleActions(Game game) {
        if (game.getState() == Game.State.DRAWING_OPENING_HANDS) {
            return game.getPlayers().stream()
                .filter(player -> !player.hasOpeningHand())
                .filter(player -> player.getHand().size() < player.getStartingHandSize())
                .map(player -> new DrawCardsAction(player.getReference(), player.getStartingHandSize() - player.getHand().size()))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
