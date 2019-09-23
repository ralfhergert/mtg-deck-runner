package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartNormalGameAction extends Action {

    private static final Logger LOG = LoggerFactory.getLogger(StartNormalGameAction.class);

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        clone.setState(Game.State.DRAWING_OPENING_HANDS);
        LOG.trace("ensuring both players have 20 life");
        clone.getPlayers().forEach(player -> player.setLife(20));
        LOG.trace("ensuring both players have a startingHandSize of 7");
        clone.getPlayers().forEach(player -> player.setStartingHandSize(7));
        return clone;
    }
}
