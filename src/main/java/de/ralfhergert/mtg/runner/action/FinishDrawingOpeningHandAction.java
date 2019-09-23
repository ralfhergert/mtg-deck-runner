package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Phase;

public class FinishDrawingOpeningHandAction extends Action {

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        clone.setStartingPlayer(clone.getPlayers().get(0).getReference());
        clone.setActivePlayer(clone.getPlayers().get(0).getReference());
        clone.setTurn(1);
        clone.setPhase(Phase.PRECOMBAT_MAIN);
        clone.setStep(null);
        clone.setState(Game.State.STARTED);
        return clone;
    }
}
