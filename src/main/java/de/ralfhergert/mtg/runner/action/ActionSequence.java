package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * This action forces all given {@link #actions} to be executed in  sequence.
 */
public class ActionSequence extends Action {

    private final List<Action> actions = new ArrayList<>();

    public ActionSequence() {}

    public ActionSequence add(Action action) {
        actions.add(action);
        return this;
    }

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        for (Action action : actions) {
            clone = action.apply(clone);
        }
        return clone;
    }
}
