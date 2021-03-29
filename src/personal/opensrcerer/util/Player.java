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
    private final short[] pointDice = {10, 10}; // 10, 10 being the question mark (?) dice

    /**
     * Shows the player's status in relation to the round.
     */
    private PlayerStatus status = PlayerStatus.AWAITING_POINT_ROLL;

    /**
     * Contains the player's score.
     */
    private int score;

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
        // Use index values to show dies
        SnakeEyes.getDiceboard().refresh(this, dice);
        // Increment to calculate real dice values
        dice[0]++;
        dice[1]++;
        // Calculate the points for this roll
        if (status.equals(PlayerStatus.AWAITING_POINT_ROLL)) {
            if (dice[0] + dice[1] == 7 || dice[0] + dice[1] == 11) {
                score += 10;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled a(n) " + (dice[0] + dice[1]) + "! +10 Points!");
                status = PlayerStatus.FINISHED_ROUND;
            } else if (dice[0] + dice[1] == 2 || dice[0] + dice[1] == 3 || dice[0] + dice[1] == 12) {
                score -= 5;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled a(n) " + (dice[0] + dice[1]) + "! -5 Points!");
                status = PlayerStatus.FINISHED_ROUND;
            } else { // Set point roll dice.
                pointDice[0] = dice[0];
                pointDice[1] = dice[1];
                SnakeEyes.getBanner().update(getPlayerName() + ", your point dice are [" + dice[0] + ", " + dice[1] + "]");
                status = PlayerStatus.PLAYING;
            }
        } else if (status.equals(PlayerStatus.PLAYING)) {
            if (dice[0] + dice[1] == pointDice[0] + pointDice[1]) {
                score += 10;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled a(n) " + (dice[0] + dice[1]) + "! +X Points!");
                status = PlayerStatus.FINISHED_ROUND;
            } else if (dice[0] + dice[1] == 7) {
                score -= 3;
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled a(n) " + (dice[0] + dice[1]) + "! -3 Points!");
                status = PlayerStatus.FINISHED_ROUND;
            } else {
                SnakeEyes.getBanner().update(getPlayerName() + ", you rolled a(n) " + (dice[0] + dice[1]) + "!");
            }
        } else {
            throw new IllegalArgumentException("Player who has finished the round cannot be rolling!");
        }
        // Decrement to calculate index dice values
        dice[0]--;
        dice[1]--;

        SnakeEyes.getDiceboard().refresh(this, dice);
        SnakeEyes.getScoreboard().refresh();
        SnakeEyes.nextTurn();
    }

    /**
     * @return If the player is a bot.
     */
    public boolean isCpu() {
        return cpuBox.isSelected();
    }

    /**
     * Sets the player's status to a AWAITING_POINT_ROLL.
     */
    public void resetStatus() {
        this.status = PlayerStatus.AWAITING_POINT_ROLL;
    }

    /**
     * @return The player's score.
     */
    public int getScore() {
        return score;
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
     * @return The Player's status.
     */
    public PlayerStatus getStatus() {
        return status;
    }
}
