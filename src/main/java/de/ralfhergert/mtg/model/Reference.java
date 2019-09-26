package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;

import java.util.Objects;
import java.util.function.Predicate;

public class Reference<T extends Referenceable<T>> implements Copyable<Reference<T>> {

    private static int next = 1;

    private final int id;
    private final Class<T> type;

    public Reference(Class<T> type) {
        this(next++, type);
    }

    private Reference(int id, Class type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public Reference deepCopy() {
        return new Reference(id, type);
    }

    public Class<T> getType() {
        return type;
    }

    public Predicate<T> asPredicate() {
        return t -> t.getReference().equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return id == ((Reference)o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
