package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.ButtonType;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class RollButton extends JPanel {

    /**
     * The JButton object wrapped by this JPanel.
     */
    private final JButton rollButton;

    /**
     * Make a custom button inside a JPanel that has a border.
     */
    public RollButton() {
        super(new GridLayout(1, 1));
        rollButton = new JButton(imagesList[11]);
        rollButton.setHorizontalAlignment(SwingConstants.CENTER);
        setButtonPalette("", rollButton);
        setMouseListener(rollButton);
        rollButton.addActionListener(getListener(ButtonType.ROLL));
        rollButton.setFocusPainted(false);
        add(rollButton);
        setBorder(BorderFactory.createLineBorder(discordLessGray, 1));
    }

    /**
     * Allows the clicking of this button.
     * This method is synchronized as it is accessed in a multithreaded context and not making it such would cause
     * different threads to toggle the button on/off.
     */
    public synchronized void allow() {
        rollButton.setEnabled(true);
    }

    /**
     * Restricts the clicking of this button.
     * This method is synchronized as it is accessed in a multithreaded context and not making it such would cause
     * different threads to toggle the button on/off.
     */
    public synchronized void restrict() {
        rollButton.setEnabled(false);
    }

    /**
     * Changes the image and listener of this button to take the user back to the starting menu.
     * This method is called once when the game is over.
     */
    public void convertToBackButton() {
        // Remove all ActionListeners on the button (there's only one)
        Arrays.stream(rollButton.getActionListeners()).forEach(rollButton::removeActionListener);
        // Replace the button's image with the "back" image
        rollButton.setIcon(PanelComponents.imagesList[13]);
        // Add a new ActionListener to the button
        rollButton.addActionListener(getListener(ButtonType.BACK));
    }
}
