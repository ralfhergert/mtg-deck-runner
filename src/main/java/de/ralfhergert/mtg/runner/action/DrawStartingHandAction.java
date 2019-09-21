package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawStartingHandAction extends Action {

    private static final Logger LOG = LoggerFactory.getLogger(DrawStartingHandAction.class);

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

    @Override
    public ActionFactory getFactory() {
        return new DrawStartingHandActionFactory();
    }

}
