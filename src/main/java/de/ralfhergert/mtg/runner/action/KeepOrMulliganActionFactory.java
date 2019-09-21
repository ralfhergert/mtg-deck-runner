package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.generic.UnexpectedError;
import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This factory checks all players, whether they are still searching for their starting hand.
 * The starting player has to decide first whether to keep or mulligan.
 */
public class KeepOrMulliganActionFactory implements ActionFactory {

    @Override
    public Collection<Action> getPossibleActions(Game game) {
        if (game.getState() == Game.State.DRAWING_OPENING_HANDS) {
            final List<Player> playersStillSearching = game.getPlayers().stream()
                .filter(player -> !player.hasOpeningHand())
                .collect(Collectors.toList());

            if (playersStillSearching.isEmpty()) {
                return Collections.emptyList();
            }

            final int highestStartHandSize = playersStillSearching.stream()
                .map(player -> player.getHand().size())
                .max(Integer::compareTo)
                .orElseThrow(() -> new UnexpectedError("could not determine highestStartingHandSize"));

            /* from all the players with the highest hand size it is the starting player
             * of the player next to starting player which has to decide first whether or
             * not to mulligan. */
            final Player playerToDecide = playersStillSearching.stream()
                .filter(player -> player.getHand().size() == highestStartHandSize)
                .findFirst()
                .orElseThrow(() -> new UnexpectedError("could not get player to decide"));

            return Arrays.asList(
                new TakeMulliganAction(playerToDecide.getReference()),
                new KeepStartingHandAction(playerToDecide.getReference())
            );
        }
        return Collections.emptyList();
    }
}
