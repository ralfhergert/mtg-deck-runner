package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;

/**
 * This interface has to be implemented by everything going onto the "Stack".
 */
public interface Resolvable<T> extends Copyable<T> {

    Game resolve(Game game);
}
