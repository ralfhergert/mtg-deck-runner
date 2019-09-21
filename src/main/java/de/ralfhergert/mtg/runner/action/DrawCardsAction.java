package de.ralfhergert.mtg.runner.action;

import de.ralfhergert.mtg.model.Game;
import de.ralfhergert.mtg.model.Player;
import de.ralfhergert.mtg.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawCardsAction extends Action {

    private static final Logger LOG = LoggerFactory.getLogger(DrawCardsAction.class);

    private final Reference<Player> playerReference;
    private final int numberOfCardsToDraw;

    public DrawCardsAction(Reference<Player> playerReference, int numberOfCardsToDraw) {
        this.playerReference = playerReference;
        this.numberOfCardsToDraw = numberOfCardsToDraw;
    }

    @Override
    public Game apply(Game game) {
        Game clone = game.deepCopy();
        final Player player = clone.getPlayer(playerReference);
        LOG.trace("Drawing {} cards for player {}", numberOfCardsToDraw, player.getName());
        for (int i = 0; i < numberOfCardsToDraw; i++) {
            if (player.getLibrary().isEmpty()) {
                LOG.info("Player {} is unable to draw from an empty library and loses the game", player.getName());
                clone.setState(Game.State.FINISHED);
                clone.setLoser(playerReference);
            }
            player.getHand().add(player.getLibrary().get(0));
        }
        return clone;
    }
}
