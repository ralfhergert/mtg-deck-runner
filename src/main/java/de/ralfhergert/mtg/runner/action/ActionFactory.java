package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;

import java.util.Collection;

public interface ActionFactory<A extends Action> {

    Collection<A> getPossibleActions(Game game);
}
