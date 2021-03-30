package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.Player;
import personal.opensrcerer.util.SnakeEyes;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class Diceboard extends JPanel {

    /**
     * JLabel for the first point die.
     */
    private final JLabel pointDie1;

    /**
     * JLabel for the second point die.
     */
    private final JLabel pointDie2;

    /**
     * JLabel for the first rolled die.
     */
    private final JLabel rollDie1;

    /**
     * JLabel for the second rolled die.
     */
    private final JLabel rollDie2;

    /**
     * JLabel for to hold the current player's name and show their current point dice.
     */
    private final JLabel pointDiceLabel;

    /**
     * JLabel for to hold the current player's name and show their current dice.
     */
    private final JLabel currentDiceLabel;

    /**
     * Returns a Diceboard.
     */
    public Diceboard() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(discordGrayer);

        // Retrieve the current player from the game.
        Player currentPlayer = SnakeEyes.getPlayerOnTurn();

        // Init all Diceboard dice with a 10.
        pointDie1 = getImageLabel(currentPlayer.getPointDice()[0] - 1);
        pointDie2 = getImageLabel(currentPlayer.getPointDice()[1] - 1);
        rollDie1 = getImageLabel(10);
        rollDie2 = getImageLabel(10);
        // Change all the Diceboard labels to default values.
        pointDiceLabel = getLabel(currentPlayer.getPlayerName() + "'s Point Dice: ", titleFont);
        currentDiceLabel = getLabel(currentPlayer.getPlayerName() + "'s Current Dice: ", titleFont);

        JPanel pointDice = getJPanel(BoxLayout.LINE_AXIS);
        JPanel rollDice = getJPanel(BoxLayout.LINE_AXIS);

        pointDice.add(Box.createRigidArea(new Dimension(20, 0))); // Side spacing L
        pointDice.add(pointDie1);
        pointDice.add(Box.createRigidArea(new Dimension(20, 0))); // Inbetween spacing
        pointDice.add(pointDie2);
        pointDice.add(Box.createRigidArea(new Dimension(20, 0))); // Side spacing R

        rollDice.add(rollDie1);
        rollDice.add(Box.createRigidArea(new Dimension(20, 0))); // Inbetween spacing
        rollDice.add(rollDie2);

        add(Box.createRigidArea(new Dimension(0, 20))); // Top Spacing
        add(pointDiceLabel);
        add(pointDice);
        add(Box.createRigidArea(new Dimension(0, 20))); // Inbetween spacing
        add(currentDiceLabel);
        add(rollDice);
        add(Box.createRigidArea(new Dimension(0, 20))); // Bottom Spacing
        setBorder(PanelComponents.getBorder());
    }

    /**
     * Update the dynamic elements of this Diceboard.
     */
    public void updateElements(Player player, short[] rollDice) {
        // Update the icons of all the dice.
        // All values are decremented to convert from value to index.
        pointDie1.setIcon(imagesList[player.getPointDice()[0] - 1]);
        pointDie2.setIcon(imagesList[player.getPointDice()[1] - 1]);
        rollDie1.setIcon(imagesList[rollDice[0] - 1]);
        rollDie2.setIcon(imagesList[rollDice[1] - 1]);

        pointDiceLabel.setText(player.getPlayerName() + "'s Point Dice: ");
        currentDiceLabel.setText(player.getPlayerName() + "'s Current Dice: ");

        revalidate();
        repaint();
    }

    /**
     * Update changes on this DiceBoard.
     * @param player The player on turn.
     * @param rollDice The dice that were just rolled.
     */
    public void refresh(Player player, short[] rollDice) {
        updateElements(player, rollDice);
    }

    /**
     * Update changes on this DiceBoard, using blank dice for the rolled dice.
     * @param player The player on turn.
     */
    public void refreshUnrolled(Player player) {
        updateElements(player, new short[] { 10, 10 });
    }
}
