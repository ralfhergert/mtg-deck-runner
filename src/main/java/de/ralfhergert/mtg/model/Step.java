package de.ralfhergert.mtg.model;

public enum Step {
    UNTAP(Phase.BEGINNING),
    UPKEEP(Phase.BEGINNING),
    DRAW(Phase.BEGINNING),
    BEGINNING_OF_COMBAT(Phase.COMBAT),
    DECLARE_ATTACKERS(Phase.COMBAT),
    DECLARE_BLOCKERS(Phase.COMBAT),
    COMBAT_DAMAGE(Phase.COMBAT),
    END_OF_COMBAT(Phase.COMBAT),
    END(Phase.ENDING),
    CLEANUP(Phase.ENDING);

    private final Phase phase;

    Step(Phase phase) {
        this.phase = phase;
    }
}
