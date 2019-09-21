package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.ai.ScryResult;
import de.ralfhergert.mtg.model.Card;
import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Player;
import de.ralfhergert.mtg.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ScryAction extends Action {

    private static final Logger LOG = LoggerFactory.getLogger(ScryAction.class);

    private final Reference<Player> playerReference;
    private final int numberOfCards;

    public ScryAction(Reference<Player> playerReference, int numberOfCards) {
        this.playerReference = playerReference;
        this.numberOfCards = numberOfCards;
    }

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        final Player player = clone.getPlayer(playerReference);
        LOG.trace("Player {} scries for {}", player.getName(), numberOfCards);
        List<Card> cardsToScryOn = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (player.getLibrary().isEmpty()) {
                LOG.info("Player {} is unable to draw from an empty library and loses the game", player.getName());
                clone.setState(Game.State.FINISHED);
                clone.setLoser(playerReference);
            }
            cardsToScryOn.add(player.getLibrary().get(0));
        }
        ScryResult scryResult = player.getPlayerAI().scry(cardsToScryOn);
        player.getLibrary().addAll(0, scryResult.getCardsOnTop());
        player.getLibrary().addAll(scryResult.getCardsOnBottom());
        return clone;
    }
}
