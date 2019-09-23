package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.generic.UnexpectedError;
import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Player;

import java.util.Collection;
import java.util.Collections;

/**
 * This factory checks whether all players have their opening hands.
 * If so the players having less hand cards get to "Scry for 1".
 * Then the starting player starts with the first turn.
 */
public class FinishDrawingOpeningHandActionFactory implements ActionFactory {

    @Override
    public Collection getPossibleActions(Game game) {
        if (game.getState() == Game.State.DRAWING_OPENING_HANDS &&
            game.getPlayers().stream().allMatch(Player::hasOpeningHand)) {
            final ActionSequence actionSequence = new ActionSequence();
            // check whether players can scry for 1.
            final int highestStartHandSize = game.getPlayers().stream()
                .map(player -> player.getHand().size())
                .max(Integer::compareTo)
                .orElseThrow(() -> new UnexpectedError("could not determine highestStartingHandSize"));

            /* All the players having a lower hand size are allowed to "scry for 1". */
            game.getPlayers().stream()
                .filter(player -> player.getHand().size() < highestStartHandSize)
                .forEach(player -> actionSequence.add(new ScryAction(player.getReference(), 1)));

            actionSequence.add(new FinishDrawingOpeningHandAction());
        }
        return Collections.emptyList();
    }
}
