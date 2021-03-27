package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.SnakeEyes;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class Diceboard extends JPanel {
    /**
     * Returns a Diceboard.
     */
    public Diceboard() {
        super();
        styleDiceboard(SnakeEyes.getPlayerOnTurn().getPlayerName(), 10, 10, 10, 10);
    }

    /**
     * Add the proper elements to this DiceBoard.
     */
    public void styleDiceboard(String playerName, int pointDie1, int pointDie2,
                               int pointDie3, int pointDie4)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(discordGrayer);

        JLabel die1 = getImageLabel(pointDie1),
                die2 = getImageLabel(pointDie2),
                die3 = getImageLabel(pointDie3),
                die4 = getImageLabel(pointDie4);

        JPanel pointDies = getJPanel(BoxLayout.LINE_AXIS);
        JPanel rollDies = getJPanel(BoxLayout.LINE_AXIS);

        pointDies.add(Box.createRigidArea(new Dimension(20, 0))); // Side spacing L
        pointDies.add(die1);
        pointDies.add(Box.createRigidArea(new Dimension(20, 0))); // Inbetween spacing
        pointDies.add(die2);
        pointDies.add(Box.createRigidArea(new Dimension(20, 0))); // Side spacing R

        rollDies.add(die3);
        rollDies.add(Box.createRigidArea(new Dimension(20, 0))); // Inbetween spacing
        rollDies.add(die4);

        add(Box.createRigidArea(new Dimension(0, 20))); // Top Spacing
        add(getLabel(playerName + "'s Point Dice: ", titleFont));
        add(pointDies);
        add(Box.createRigidArea(new Dimension(0, 20))); // Inbetween spacing
        add(getLabel(playerName + "'s Current Dice: ", titleFont));
        add(rollDies);
        add(Box.createRigidArea(new Dimension(0, 20))); // Bottom Spacing
        setBorder(PanelComponents.getBorder());
    }

    /**
     * Update changes on this DiceBoard.
     */
    public void refresh(String playerName, int pointDie1, int pointDie2,
                        int pointDie3, int pointDie4)
    {
    styleDiceboard(playerName, pointDie1, pointDie2, pointDie3, pointDie4);
        revalidate();
        repaint();
    }

    /**
     * Update changes on this DiceBoard.
     */
    public void refreshUnrolled(String playerName) {
        styleDiceboard(playerName, 10, 10, 10, 10);
        revalidate();
        repaint();
    }
}
