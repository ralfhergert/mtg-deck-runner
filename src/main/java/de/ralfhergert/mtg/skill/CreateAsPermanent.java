package de.ralfhergert.mtg.skill;

import de.ralfhergert.mtg.model.Card;
import de.ralfhergert.mtg.model.Permanent;

public interface CreateAsPermanent extends Skill {

    Permanent asPermanent(Card card);
}
