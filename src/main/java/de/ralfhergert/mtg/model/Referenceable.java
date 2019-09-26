package de.ralfhergert.mtg.model;

public interface Referenceable<T extends Referenceable<T>> {

    Reference<T> getReference();
}
