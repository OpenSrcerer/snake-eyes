package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.ButtonType;
import personal.opensrcerer.util.JPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Start {

    private static final Integer[] playerChoices = {1, 2, 3, 4, 5, 6, 7, 8};

    public static JPlayer[] allPlayers;
    public static JPanel playerList;
    private static JComboBox<Integer> playersBox;

    public static void setComponents(final Container pane) {
        JPanel titlePanel = PanelComponents.getJPanel(BoxLayout.PAGE_AXIS);
        JPanel playersSelection = PanelComponents.getJPanel();
        JPanel buttonPanel = PanelComponents.getJPanel(BoxLayout.LINE_AXIS);
        JPanel playButton = PanelComponents.getBorderedButton("Play", ButtonType.START);
        playButton.setPreferredSize(new Dimension(75, 75));
        JPanel helpButton = PanelComponents.getBorderedButton("Help", ButtonType.HELP);
        helpButton.setPreferredSize(new Dimension(75, 75));
        JPanel creditsButton = PanelComponents.getBorderedButton("Credits", ButtonType.CREDITS);
        creditsButton.setPreferredSize(new Dimension(75, 75));
        JLabel label = PanelComponents.getLabel("Number of players:", PanelComponents.titleFont);
        JPanel totalPanel = PanelComponents.getJPanel(BoxLayout.PAGE_AXIS);
        JPanel bottomPanel = PanelComponents.getJPanel(new BorderLayout());

        playerList = PanelComponents.getJPanel(BoxLayout.PAGE_AXIS);
        playerList.setPreferredSize(new Dimension(500, 320));
        playerList.setBorder(PanelComponents.getBorder("Player List"));

        playersBox = new JComboBox<>(playerChoices);
        playersBox.addActionListener(playerListener());

        // Add default player
        JPlayer defaultPlayer = new JPlayer(1);
        playerList.add(defaultPlayer);
        allPlayers = new JPlayer[1];
        allPlayers[0] = defaultPlayer;

        buttonPanel.add(helpButton);
        buttonPanel.add(playButton);
        buttonPanel.add(creditsButton);

        playersSelection.add(label);
        playersSelection.add(playersBox);

        titlePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        titlePanel.add(PanelComponents.getLogo());
        titlePanel.add(Box.createRigidArea(new Dimension(0, 25)));

        bottomPanel.add(PanelComponents.getSpeakerUnmute(), BorderLayout.EAST);
        bottomPanel.add(new JSlider(), BorderLayout.EAST);

        totalPanel.add(titlePanel);
        totalPanel.add(PanelComponents.getSeparator());
        totalPanel.add(buttonPanel);
        totalPanel.add(PanelComponents.getSeparator());
        totalPanel.add(playersSelection);
        totalPanel.add(playerList);
        totalPanel.add(bottomPanel);

        pane.add(totalPanel);
    }

    private static ActionListener playerListener() {
        return e -> {
            playerList.removeAll();
            int selection = playersBox.getSelectedIndex() + 1;
            allPlayers = new JPlayer[selection];

            for (int index = 0; index < selection; ++index) {
                JPlayer newPlayer = new JPlayer(index + 1);
                allPlayers[index] = newPlayer;
                playerList.add(newPlayer);
            }

            playerList.revalidate();
            playerList.repaint();
        };
    }
}
