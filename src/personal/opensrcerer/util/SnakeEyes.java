package personal.opensrcerer.util;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Represents one full SnakeEyes game.
 */
public final class SnakeEyes {

    /**
     * An array that contains all the JPlayers in the game.
     */
    private final JPlayer[] players;

    /**
     * The total rounds in this game.
     */
    private final int totalRounds;

    /**
     * This game's current round.
     */
    private int currentRound;

    public SnakeEyes(JPlayer[] players, int totalRounds) {
        this.players = players;
        this.totalRounds = totalRounds;
    }

    /**
     * @return The current round of the game.
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * @return The total rounds of the game.
     */
    public int getTotalRounds() {
        return totalRounds;
    }

    /**
     * @return All the players in the game as a Stream.
     */
    public Stream<JPlayer> getPlayers() {
        return Arrays.stream(players);
    }
}
