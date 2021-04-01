package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.ButtonType;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * The JPanel that contains every UI element that will be shown to the user while in-game.
 */
public class StartPanel {

    /**
     * Singleton startPanel instance.
     */
    private static final JPanel startPanel;

    /**
     * Bottom panel instance.
     */
    private static final BottomPanel bottomPanel;

    static {
        JPanel playerList = getJPanel(BoxLayout.PAGE_AXIS);
        playerList.setPreferredSize(new Dimension(500, 320));
        playerList.setBorder(getBorder("Player List"));

        PlayerComboBox<Integer> playersBox = new PlayerComboBox<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8}, playerList);
        JComboBox<Integer> roundsBox = getComboBox(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});

        final JPanel selections = getJPanel(BoxLayout.PAGE_AXIS),
                titlePanel = getJPanel(BoxLayout.PAGE_AXIS),
                buttonPanel = getJPanel(BoxLayout.LINE_AXIS),
                playButton = getPlayButton(playersBox, roundsBox),
                helpButton = getBorderedButton("Help", ButtonType.HELP),
                creditsButton = getBorderedButton("Credits", ButtonType.CREDITS),
                playersSelection = getJPanel(),
                roundsSelection = getJPanel();
        startPanel = getJPanel(BoxLayout.PAGE_AXIS);
        bottomPanel = new BottomPanel();

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

        startPanel.add(titlePanel);
        startPanel.add(getSeparator());
        startPanel.add(buttonPanel);
        startPanel.add(getSeparator());
        startPanel.add(selections);
        startPanel.add(playerList);
        startPanel.add(bottomPanel);
    }

    /**
     * Set all the components of the given ContentPane to the StartPanel components.
     * @param pane ContentPane of MainWindow's JFrame.
     */
    public static void setComponents(final Container pane) {
        pane.add(startPanel);
        bottomPanel.refresh();
    }
}
