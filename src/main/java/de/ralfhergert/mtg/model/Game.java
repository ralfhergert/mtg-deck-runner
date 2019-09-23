package de.ralfhergert.mtg.model;

import de.ralfhergert.generic.UnexpectedError;
import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.cloning.CopyableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class Game implements Copyable<Game> {

    private static final Logger LOG = LoggerFactory.getLogger(Game.class);

    public enum GameMode {
        NORMAL /* 2 Players, 60 cards minimum deck size */
    }

    public enum State {
        SETUP,
        DRAWING_OPENING_HANDS,
        STARTED,
        FINISHED
    }

    private GameMode gameMode = GameMode.NORMAL;
    private State state = State.SETUP;
    private CopyableList<Player> players = new CopyableList<>();

    private Reference<Player> startingPlayer;
    private Reference<Player> activePlayer;
    private int turn;
    private Phase phase;
    private Step step;

    @Override
    public Game deepCopy() {
        final Game game = new Game();
        game.gameMode = gameMode;
        game.state = state;
        game.players = players.deepCopy();
        return game;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        LOG.debug("Game state={}", state);
        this.state = state;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getPlayer(Reference<Player> playerReference) {
        return players.stream()
            .filter(player -> player.getReference().equals(playerReference))
            .findFirst()
            .orElseThrow(() -> new UnexpectedError("could not find referenced player"));
    }

    public void setLoser(Reference<Player> playerReference) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Reference<Player> getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(Reference<Player> startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public Reference<Player> getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Reference<Player> activePlayer) {
        this.activePlayer = activePlayer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
