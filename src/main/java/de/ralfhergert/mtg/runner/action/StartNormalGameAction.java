package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;

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

    @Override
    public ActionFactory getFactory() {
        return new StartGameActionFactory();
    }

    private static class StartGameActionFactory implements ActionFactory {
        @Override
        public Collection<Action> getPossibleActions(Game game) {
            if (game.getState() == Game.State.SETUP &&
                game.getGameMode() == Game.GameMode.NORMAL &&
                game.getPlayers().size() == 2) {
                return Collections.singleton(new StartNormalGameAction());
            }
            return Collections.emptyList();
        }
    }
}
