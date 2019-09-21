package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Player;
import de.ralfhergert.mtg.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class TakeMulliganAction extends PlayerChoiceAction {

    private static final Logger LOG = LoggerFactory.getLogger(TakeMulliganAction.class);

    public TakeMulliganAction(Reference<Player> playerReference) {
        super(playerReference);
    }

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        final Player player = clone.getPlayer(getPlayerReference());
        LOG.trace("Player {} takes a mulligan", player.getName());
        // hand goes back into the library
        player.getLibrary().addAll(player.getHand());
        player.getHand().clear();
        // shuffle the library
        Collections.shuffle(player.getLibrary());
        player.setStartingHandSize(player.getStartingHandSize() - 1);
        if (player.getStartingHandSize() <= 0) {
            // rule 103.4 player must keep an empty hand
            player.setHasOpeningHand(true);
        }
        return clone;
    }
}
