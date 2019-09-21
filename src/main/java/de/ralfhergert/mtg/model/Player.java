package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.cloning.CopyableList;
import de.ralfhergert.mtg.ai.PlayerAI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player implements Copyable<Player> {

    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    private final Reference<Player> reference;

    private PlayerAI playerAI;

    private String name;
    private int life = 20;
    private int startingHandSize = 7;
    private boolean hasOpeningHand;
    private boolean hasPlayedLandThisTurn;

    private CopyableList<Card> library = new CopyableList<>();
    private CopyableList<Card> hand = new CopyableList<>();

    public Player() {
        this(new Reference<>(Player.class));
    }

    private Player(Reference<Player> reference) {
        this.reference = reference;
    }

    @Override
    public Player deepCopy() {
        final Player player = new Player(reference);
        player.playerAI = playerAI; // do not create a deep copy of the AI.
        player.name = name;
        player.life = life;
        player.startingHandSize = startingHandSize;
        player.hasOpeningHand = hasOpeningHand;
        player.hasPlayedLandThisTurn = hasPlayedLandThisTurn;
        player.library = library.deepCopy();
        player.hand = hand.deepCopy();
        return new Player();
    }

    public Reference<Player> getReference() {
        return reference;
    }

    public PlayerAI getPlayerAI() {
        return playerAI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        LOG.debug("Player {} life={}", name, life);
        this.life = life;
    }

    public int getStartingHandSize() {
        return startingHandSize;
    }

    public void setStartingHandSize(int startingHandSize) {
        LOG.debug("Player {} startingHandSize={}", name, startingHandSize);
        this.startingHandSize = startingHandSize;
    }

    public boolean hasOpeningHand() {
        return hasOpeningHand;
    }

    public void setHasOpeningHand(boolean hasOpeningHand) {
        this.hasOpeningHand = hasOpeningHand;
    }

    public boolean hasPlayedLandThisTurn() {
        return hasPlayedLandThisTurn;
    }

    public void setHasPlayedLandThisTurn(boolean hasPlayedLandThisTurn) {
        this.hasPlayedLandThisTurn = hasPlayedLandThisTurn;
    }

    public CopyableList<Card> getLibrary() {
        return library;
    }

    public CopyableList<Card> getHand() {
        return hand;
    }
}
