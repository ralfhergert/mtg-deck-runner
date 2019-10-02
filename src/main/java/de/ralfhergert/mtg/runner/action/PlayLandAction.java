package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.*;
import de.ralfhergert.mtg.skill.CreateAsPermanent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayLandAction extends PlayerChoiceAction {

    private static final Logger LOG = LoggerFactory.getLogger(PlayLandAction.class);

    private final Reference<Card> cardReference;

    public PlayLandAction(Reference<Player> playerReference, Reference<Card> cardReference) {
        super(playerReference);
        this.cardReference = cardReference;
    }

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        final Player player = clone.getPlayer(getPlayerReference());
        final Card card = player.getCard(cardReference);
        LOG.trace("Player {} plays land {}", player.getName(), card.getName());
        if (!player.getHand().remove(card)) {
            throw new AssertionError("referenced card was not in the player's hands");
        }
        card.getSkills(skill -> skill instanceof CreateAsPermanent)
            .forEach(skill -> player.getBattleField().add(((CreateAsPermanent)skill).asPermanent(card)));

        player.setHasPlayedLandThisTurn(true);
        return clone;
    }
}
