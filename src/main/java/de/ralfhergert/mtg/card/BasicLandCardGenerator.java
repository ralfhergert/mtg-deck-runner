package de.ralfhergert.mtg.card;

import de.ralfhergert.mtg.ability.TapForMana;
import de.ralfhergert.mtg.model.Card;
import de.ralfhergert.mtg.model.ManaType;
import de.ralfhergert.mtg.model.Permanent;
import de.ralfhergert.mtg.skill.CreateAsPermanent;

import java.util.List;
import java.util.function.Supplier;

public class BasicLandCardGenerator implements Supplier<Card> {

    private String name;
    private List<Card.Type> types;
    private ManaType manaType;

    public String getName() {
        return name;
    }

    public BasicLandCardGenerator setName(String name) {
        this.name = name;
        return this;
    }

    public List<Card.Type> getTypes() {
        return types;
    }

    public BasicLandCardGenerator setTypes(List<Card.Type> types) {
        this.types = types;
        return this;
    }

    public ManaType getManaType() {
        return manaType;
    }

    public BasicLandCardGenerator setManaType(ManaType manaType) {
        this.manaType = manaType;
        return this;
    }

    @Override
    public Card get() {
        return new Card()
            .setName(name)
            .setTypes(types)
            .addSkill((CreateAsPermanent) card -> new Permanent()
                .setName(card.getName())
                .setType(Permanent.Type.Land)
                .addAbility(new TapForMana().setAmount(1).setManaType(manaType))
                .setCreatedFromCard(card));
    }
}
