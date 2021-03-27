package personal.opensrcerer.util;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Represents one full SnakeEyes game.
 * Uses the Singleton design pattern.
 */
public final class SnakeEyes {
    /**
     * An array that contains all the JPlayers in the game.
     */
    private static Player[] players = null;

    /**
     * The total rounds in this game.
     */
    private static int totalRounds;

    /**
     * This game's current round.
     */
    private static int currentRound = 1;

    /**
     * Resets the singleton instance of the ongoing game.
     */
    public static void resetGame(Player[] players, int totalRounds) {
        SnakeEyes.players = players;
        SnakeEyes.totalRounds = totalRounds;
    }

    private SnakeEyes() {
    }

    /**
     * @return The current round of the game.
     */
    public static int getCurrentRound() {
        return currentRound;
    }

    /**
     * @return The total rounds of the game.
     */
    public static int getTotalRounds() {
        return totalRounds;
    }

    /**
     * Advance the game to the next round.
     * If there are no more rounds, the game finishes.
     */
    public static synchronized void nextRound() {
        if (currentRound <= totalRounds) {
            currentRound++;
        } else {
            // TODO finish the game
        }
    }

    /**
     * @return The number of players in this game.
     */
    public static int size() {
        return players.length;
    }

    /**
     * @return All the players in the game as a Stream.
     */
    public static Stream<Player> getPlayers() {
        return Arrays.stream(players);
    }
}
