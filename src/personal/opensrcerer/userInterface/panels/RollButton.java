package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.ButtonType;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class RollButton extends JPanel {

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
     * Toggle the availability of this button.
     */
    public synchronized void toggle() {
        rollButton.setEnabled(!rollButton.isEnabled());
    }
}