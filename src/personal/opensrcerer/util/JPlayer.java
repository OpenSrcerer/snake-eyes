package personal.opensrcerer.util;

import javax.swing.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * A custom JComponent that shows players on a leaderboard, and contains information about a player.
 * The unique detail about this Object is that it is used both as a GUI component and also as a bean to store information.
 */
public class JPlayer extends JPanel {

    /**
     * Shows whether the JPlayer is a bot.
     */
    private final JCheckBox cpuBox;

    /**
     * Contains the JTextField to retrieve the player's name from.
     */
    private final JTextField field;

    /**
     * Contains the player's score.
     */
    private int score;

    public JPlayer(int playerNumber) {
        super();
        setBackground(discordGrayer);

        this.cpuBox = getCheckBox("Bot", false);
        this.field = getTextField("Player " + playerNumber, 10);

        add(getLabel("Player " + playerNumber + " // Name:", descriptionFont));
        add(field);
        add(cpuBox);
    }

    /**
     * @param scoreToAdd Points to add to the score.
     */
    public void addToScore(int scoreToAdd) {
        score += scoreToAdd;
    }

    /**
     * @param scoreToRemove Points to remove from the score.
     */
    public void removeFromScore(int scoreToRemove) {
        score -= scoreToRemove;
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
     * @return If the player is a bot.
     */
    public boolean isCpu() {
        return cpuBox.isSelected();
    }
}
