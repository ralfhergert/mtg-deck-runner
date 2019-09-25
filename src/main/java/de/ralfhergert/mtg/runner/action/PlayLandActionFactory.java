package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Card;
import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Phase;
import de.ralfhergert.mtg.model.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This factory lets player draw cards if they have not yet their opening hand
 * and less cards then their supposed startingHandSize.
 */
public class PlayLandActionFactory implements ActionFactory {

    @Override
    public Collection getPossibleActions(Game game) {
        if (game.getState() == Game.State.STARTED &&
            (game.getPhase() == Phase.PRECOMBAT_MAIN || game.getPhase() == Phase.POSTCOMBAT_MAIN) &&
            game.getStack().isEmpty()) {
            final Player activePlayer = game.getPlayer(game.getActivePlayer());
            if (!activePlayer.hasPlayedLandThisTurn()) {
                return activePlayer.getHand().stream()
                    .filter(card -> card.getType() == Card.Type.Land)
                    .map(card -> new PlayLandAction(activePlayer.getReference(), card.getReference()))
                    .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
