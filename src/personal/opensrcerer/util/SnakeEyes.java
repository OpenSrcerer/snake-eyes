package personal.opensrcerer.util;

import personal.opensrcerer.actions.RollRequest;
import personal.opensrcerer.userInterface.panels.Diceboard;
import personal.opensrcerer.userInterface.panels.RollButton;
import personal.opensrcerer.userInterface.panels.Scoreboard;
import personal.opensrcerer.util.circularlist.CircularLinkedList;

import java.util.stream.Stream;

/**
 * Represents one full SnakeEyes game.
 * Uses the Singleton design pattern.
 */
public final class SnakeEyes {

    /**
     * The Roll Button for this game.
     */
    private static final RollButton rollButton = new RollButton();

    /**
     * The Diceboard for this game.
     */
    private static Diceboard diceboard;

    /**
     * The Scoreboard for this game.
     */
    private static Scoreboard scoreboard;

    /**
     * A custom Circular Linked List that contains all the JPlayers in the game.
     */
    private static CircularLinkedList<Player> players = null;

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
        SnakeEyes.players = new CircularLinkedList<>(players);
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
        return players.getCurrent();
    }

    /**
     * @return All the players in the game as a Stream.
     */
    public static Stream<Player> getPlayers() {
        return players.getAll().stream();
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
     * @return This game's RollButton.
     */
    public static RollButton getRollButton() {
        return rollButton;
    }

    /**
     * Advances the turn to the next player.
     * If it's the turn of the last player on the list, the round advances, or the game ends.
     */
    public static synchronized void nextTurn() {
        Player currentPlayer = players.next(); // Advance the turn

        // Loop to skip over players who have finished the round
        while (currentPlayer.getStatus().equals(PlayerStatus.FINISHED_ROUND)) {
            currentPlayer = players.next(); // Advance the turn
        }

        if (players.isAtFirst()) { // If the list of players just looped once
            if (nextRound() && currentPlayer.isCpu()) { // Advance the round
                new RollRequest(currentPlayer); // Roll the bot player automatically
            }
        }
    }

    /**
     * Advance the game to the next round.
     * If there are no more rounds, the game finishes.
     * @return True if game finished, false if it didn't.
     */
    public static boolean nextRound() {
        if (currentRound < totalRounds) { // If the current round is at a lesser value than the total rounds
            currentRound++; // Increment
            getPlayers().forEach(Player::resetStatus); // Resets the player's status to unrolled
        } else {
            // TODO finish the game
            return true;
        }
        return false;
    }

    /**
     * @return The number of players in this game.
     */
    public static int size() {
        return players.size();
    }
}
