package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;

import java.util.Collection;
import java.util.Collections;

class StartGameActionFactory implements ActionFactory {

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
