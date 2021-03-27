package personal.opensrcerer.util;

import personal.opensrcerer.userInterface.panels.Diceboard;
import personal.opensrcerer.userInterface.panels.Scoreboard;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Represents one full SnakeEyes game.
 * Uses the Singleton design pattern.
 */
public final class SnakeEyes {

    /**
     * The Diceboard for this game.
     */
    private static Diceboard diceboard;

    /**
     * The Scoreboard for this game.
     */
    private static Scoreboard scoreboard;

    /**
     * An array that contains all the JPlayers in the game.
     */
    private static Player[] players = null;

    /**
     * A variable representing whose turn it is.
     */
    private static int currentTurn = 0;

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
        SnakeEyes.currentRound = 1;
        SnakeEyes.diceboard = new Diceboard();
        SnakeEyes.scoreboard = new Scoreboard();
    }

    private SnakeEyes() {
    }

    /**
     * @return The player whose turn it is.
     */
    public static Player getPlayerOnTurn() {
        return players[currentTurn];
    }

    /**
     * @return All the players in the game as a Stream.
     */
    public static Stream<Player> getPlayers() {
        return Arrays.stream(players);
    }

    /**
     * @return The Scoreboard for this game.
     */
    public static Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     * @return The Diceboard for this game.
     */
    public static Diceboard getDiceboard() {
        return diceboard;
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
     * Advances the turn to the next player.
     * If it's the turn of the last player on the list, the round advances, or the game ends.
     */
    public static synchronized void nextTurn() {
        if (currentTurn <= players.length - 1) {
            currentTurn++;
        } else {
            nextRound();
        }
    }

    /**
     * Advance the game to the next round.
     * If there are no more rounds, the game finishes.
     */
    public static void nextRound() {
        if (currentRound < totalRounds) {
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
}
