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
        bannerText = getLabel("Round " + SnakeEyes.getCurrentRound() + " // Roll, " +
                        SnakeEyes.getPlayerOnTurn().getPlayerName() + "!", bigTitleFont);
        add(bannerText, BorderLayout.CENTER);
    }

    /**
     * Updates the banner's message to the preset default message.
     */
    public void update() {
        bannerText.setText("Round " + SnakeEyes.getCurrentRound() + " // Roll, " +
                SnakeEyes.getPlayerOnTurn().getPlayerName() + "!");
        revalidate();
        repaint();
    }

    /**
     * Updates the banner's message to a given String.
     * @param text Text to update this Banner to.
     */
    public void update(String text) {
        bannerText.setText(text);
        revalidate();
        repaint();
    }
}
