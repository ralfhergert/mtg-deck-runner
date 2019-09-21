package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Player;
import de.ralfhergert.mtg.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeepStartingHandAction extends PlayerChoiceAction {

    private static final Logger LOG = LoggerFactory.getLogger(KeepStartingHandAction.class);

    public KeepStartingHandAction(Reference<Player> playerReference) {
        super(playerReference);
    }

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        final Player player = clone.getPlayer(getPlayerReference());
        LOG.trace("Player {} keeps starting hand", player.getName());
        player.setHasOpeningHand(true);
        return clone;
    }
}
