/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.SnakeEyes;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * The top banner that contains the announcement text.
 */
public class Banner extends JPanel {

    /**
     * The label that contains this banner's text.
     */
    private final JLabel bannerText;

    public Banner() {
        super();
        setBackground(discordGrayer);
        bannerText = getLabel("Round (" + SnakeEyes.getCurrentRound() + "/" + SnakeEyes.getTotalRounds() + ") // Roll, " +
                SnakeEyes.getPlayerOnTurn().getPlayerName() + "!", bigTitleFont);
        add(bannerText, BorderLayout.CENTER);
    }

    /**
     * Updates the banner's message to the preset default message.
     */
    public void update() {
        // Show the round message if the current player is not a bot.
        if (!SnakeEyes.getPlayerOnTurn().isCpu()) {
            bannerText.setText("Round (" + SnakeEyes.getCurrentRound() + "/" + SnakeEyes.getTotalRounds() + ") // Roll, " +
                    SnakeEyes.getPlayerOnTurn().getPlayerName() + "!");
        }
        revalidate();
        repaint();
    }

    /**
     * Updates the banner's message to a given String.
     * @param text Text to update this Banner to.
     */
    public void update(String text, boolean skipForBots) {
        // Show a custom message if the current player is not a bot.
        if (!SnakeEyes.getPlayerOnTurn().isCpu()) {
            bannerText.setText(text);
        } else if (!skipForBots) {
            bannerText.setText(text);
        }
        revalidate();
        repaint();
    }
}
