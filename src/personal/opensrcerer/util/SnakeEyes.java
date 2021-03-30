package personal.opensrcerer.util;

import personal.opensrcerer.userInterface.panels.Banner;
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
    private static RollButton rollButton;

    /**
     * The banner that contains announcement text for this game.
     */
    private static Banner banner;

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
    private static int currentRound;

    /**
     * Resets the singleton instance of the ongoing game.
     */
    public static void resetGame(Player[] players, int totalRounds) {
        // Values first
        SnakeEyes.players = new CircularLinkedList<>(players);
        SnakeEyes.totalRounds = totalRounds;
        SnakeEyes.currentRound = 1;
        // Instantiate GUI Elements last
        SnakeEyes.rollButton = new RollButton();
        SnakeEyes.banner = new Banner();
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
     * @return The Banner for this game.
     */
    public static Banner getBanner() {
        return banner;
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
    public static void nextTurn() {
        nextPlayer(); // Go to the next player

        /*if (players.isAtFirst()) { // If the list of players just looped once
            if (nextRound() && getPlayerOnTurn().isCpu()) { // Advance the round
                new RollRequest(getPlayerOnTurn()); // Roll the bot player automatically
            }
        }*/
    }

    /**
     * Checks if the round is about to end, then advances the turn to the next player.
     */
    private static void nextPlayer() {
        // Filters the Stream of Players to contain only the players who have finished and then counts the total
        int finishedPlayers = players.getElementsThat(player -> player.getStatus().equals(PlayerStatus.FINISHED_ROUND));
        if (finishedPlayers == size()) { // If all players have finished rolling
            nextRound(); // Go to the next round, or finish the game
        } else {
            // Advance the turn to the next AVAILABLE player that hasn't finished
            players.advanceTo(player -> !player.getStatus().equals(PlayerStatus.FINISHED_ROUND));
        }
    }

    /**
     * Advance the game to the next round.
     * If there are no more rounds, the game finishes.
     */
    private static void nextRound() {
        if (currentRound < totalRounds) { // If the current round is at a lesser value than the total rounds
            currentRound++; // Advance to the next round
            getPlayers().forEach(Player::resetStatus); // Resets the players' statuses to unrolled
            players.setToFirst(); // Give the turn to the first player
        } else {
            System.out.println("Game has finished.");
            // TODO finish the game
        }
    }

    /**
     * @return The number of players in this game.
     */
    public static int size() {
        return players.size();
    }
}
