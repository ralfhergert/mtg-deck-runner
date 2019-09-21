package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;

public abstract class Action {

    public abstract Game apply(Game game);
}
