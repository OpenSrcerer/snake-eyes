package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.ButtonType;
import personal.opensrcerer.util.PlayerComboBox;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class StartPanel {

    public static void setComponents(final Container pane) {
        JPanel playerList = getJPanel(BoxLayout.PAGE_AXIS);
        playerList.setPreferredSize(new Dimension(500, 320));
        playerList.setBorder(getBorder("Player List"));

        PlayerComboBox<Integer> playersBox = new PlayerComboBox<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8}, playerList);
        JComboBox<Integer> roundsBox = getComboBox(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});

        JPanel totalPanel = getJPanel(BoxLayout.PAGE_AXIS),
                selections = getJPanel(BoxLayout.PAGE_AXIS),
                titlePanel = getJPanel(BoxLayout.PAGE_AXIS),
                buttonPanel = getJPanel(BoxLayout.LINE_AXIS),
                playButton = getPlayButton(playersBox, roundsBox),
                helpButton = getBorderedButton("Help", ButtonType.HELP),
                creditsButton = getBorderedButton("Credits", ButtonType.CREDITS),
                playersSelection = getJPanel(),
                roundsSelection = getJPanel();

        playButton.setPreferredSize(new Dimension(75, 75));
        helpButton.setPreferredSize(new Dimension(75, 75));
        creditsButton.setPreferredSize(new Dimension(75, 75));

        buttonPanel.add(helpButton);
        buttonPanel.add(playButton);
        buttonPanel.add(creditsButton);

        playersSelection.add(getLabel("Number of players:", titleFont));
        playersSelection.add(playersBox);

        roundsSelection.add(getLabel("Number of rounds:", titleFont));
        roundsSelection.add(roundsBox);

        selections.add(playersSelection);
        selections.add(roundsSelection);

        titlePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        titlePanel.add(getLogo());
        titlePanel.add(Box.createRigidArea(new Dimension(0, 15)));

        totalPanel.add(titlePanel);
        totalPanel.add(getSeparator());
        totalPanel.add(buttonPanel);
        totalPanel.add(getSeparator());
        totalPanel.add(selections);
        totalPanel.add(playerList);
        totalPanel.add(getBottomPanel());

        pane.add(totalPanel);
    }
}
