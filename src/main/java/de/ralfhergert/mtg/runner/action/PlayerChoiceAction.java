package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Player;
import de.ralfhergert.mtg.model.Reference;

public abstract class PlayerChoiceAction extends Action {

    private final Reference<Player> playerReference;

    public PlayerChoiceAction(Reference<Player> playerReference) {
        this.playerReference = playerReference;
    }

    public Reference<Player> getPlayerReference() {
        return playerReference;
    }
}
