/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.util;

import javax.swing.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * A custom JComponent that shows players on a leaderboard, and contains information about a player.
 * The unique detail about this Object is that it is used both as a GUI component and also as a bean to store information.
 */
public class Player extends JPanel {

    /**
     * Shows whether the JPlayer is a bot.
     */
    private final JCheckBox cpuBox;

    /**
     * Contains the JTextField to retrieve the player's name from.
     */
    private final JTextField field;

    /**
     * Contains the point dice for a player.
     */
    private final short[] pointDice = {11, 11}; // 10, 10 being the question mark (?) dice indexes

    /**
     * Shows the player's status in relation to the round.
     */
    private PlayerStatus status = PlayerStatus.AWAITING_POINT_ROLL;

    /**
     * Shows how many times a player has consecutively rolled without changing their score.
     */
    private int consecutiveRolls;

    /**
     * Contains the player's score.
     */
    private int score;

    /**
     * Create a new player, ordered by a number from 1 - 8 on the player list.
     * @param playerNumber The ordinal number.
     */
    public Player(int playerNumber) {
        super();
        setBackground(discordGrayer);

        this.cpuBox = getCheckBox("Bot", false);
        this.field = getTextField("Player " + playerNumber, 10);

        add(getLabel("Player " + playerNumber + " // Name:", descriptionFont));
        add(field);
        add(cpuBox);
    }

    /**
     * Rolls the player's dice and performs the appropriate action.
     * @param dice The dice that were just rolled.
     */
    public synchronized void roll(short[] dice) {
        // Show dies that were just rolled to the player(s)
        SnakeEyes.getDiceboard().refresh(dice);
        // Calculate the sum of the dice
        int diceSum = dice[0] + dice[1];

        if (status.equals(PlayerStatus.AWAITING_POINT_ROLL)) {
            // Actions appropriate for the first roll of the round
            if (diceSum == 7 || diceSum == 11) {
                score += 10;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled " + getArticle(diceSum) + " " +
                        diceSum + "! (+10 Points)", true);
                status = PlayerStatus.FINISHED_ROUND;
            } else if (diceSum == 2 || diceSum == 3 || diceSum == 12) {
                score -= 5;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled " + getArticle(diceSum) + " " + diceSum +
                        "! (-5 Points)", true);
                status = PlayerStatus.FINISHED_ROUND;
            } else { // Set point roll dice.
                pointDice[0] = dice[0];
                pointDice[1] = dice[1];
                SnakeEyes.getBanner().update(getPlayerName() + ", your point dice are [" + dice[0] +
                                ", " + dice[1] + "].", true);
                status = PlayerStatus.PLAYING;
            }
        } else if (status.equals(PlayerStatus.PLAYING)) {
            // Actions appropriate for the other rolls of the round
            if (diceSum == pointDice[0] + pointDice[1]) {
                score += 10 - consecutiveRolls;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled " + getArticle(diceSum) +
                        " " + diceSum + "! (" + (10 - consecutiveRolls) + " Points)", true);
                status = PlayerStatus.FINISHED_ROUND;
            } else if (diceSum == 7) {
                score -= 3;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled " + getArticle(diceSum) +
                        " " + diceSum + "! (-3 Points)", true);
                status = PlayerStatus.FINISHED_ROUND;
            } else {
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled " + getArticle(diceSum) +
                        " " + diceSum + "!", true);
            }
        } else {
            throw new IllegalArgumentException("Player who has finished the round cannot be rolling!");
        }

        // Increase the number of rolls that were made
        ++consecutiveRolls;
        SnakeEyes.getScoreboard().refresh();
        SnakeEyes.getDiceboard().refresh(dice);
        SnakeEyes.nextTurn();
    }

    /**
     * @return The Player's status.
     */
    public PlayerStatus getStatus() {
        return status;
    }

    /**
     * @return The player's name.
     */
    public String getPlayerName() {
        return field.getText();
    }

    /**
     * @return The point dice for this player.
     */
    public short[] getPointDice() {
        return pointDice;
    }

    /**
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return If the player is a bot.
     */
    public boolean isCpu() {
        return cpuBox.isSelected();
    }

    /**
     * Sets the player's status to a AWAITING_POINT_ROLL, reset consecutive rolls and the point dice.
     * @param resetScore If the score should be reset.
     */
    public void resetStatus(boolean resetScore) {
        this.status = PlayerStatus.AWAITING_POINT_ROLL;
        this.pointDice[0] = 11;
        this.pointDice[1] = 11;
        this.consecutiveRolls = 0;
        if (resetScore) {
            this.score = 0;
        }
    }

    /**
     * @param sum The sum of the dice for this roll.
     * @return The appropriate article for the number.
     */
    private String getArticle(int sum) {
        return switch (sum) {
            case 8, 11 -> "an";
            default -> "a";
        };
    }
}
