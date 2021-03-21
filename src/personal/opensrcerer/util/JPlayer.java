package personal.opensrcerer.util;

import javax.swing.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class JPlayer extends JPanel {

    private final JCheckBox cpuBox;

    private final JTextField field;

    public JPlayer(int playerNumber) {
        super();
        setBackground(discordGrayer);

        this.cpuBox = getCheckBox("Bot", false);
        this.field = getTextField("Player " + playerNumber, 10);

        add(getLabel("Player " + playerNumber + " // Name:", descriptionFont));
        add(field);
        add(cpuBox);
    }

    public String getPlayerName() {
        return field.getText();
    }

    public boolean isCpu() {
        return cpuBox.isSelected();
    }
}
