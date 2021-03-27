package personal.opensrcerer.util;

import personal.opensrcerer.userInterface.panels.PanelComponents;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An extension to JComboBox that contains a List of current JPlayers.
 * @param <E> The type of element that this ComboBox will hold.
 */
public class PlayerComboBox<E> extends JComboBox<E> {
    /**
     * An ArrayList that contains the currently selected players.
     */
    private final List<JPlayer> players = new ArrayList<>();

    /**
     * Create a new PlayerComboBox with the given parameters.
     * @param selections The available choices in the ComboBox.
     * @param playerList The list of players to update with changes.
     */
    public PlayerComboBox(E[] selections, JPanel playerList) {
        super(selections);
        setBackground(PanelComponents.discordGrayer);
        setFont(PanelComponents.descriptionFont);

        // Add default player
        JPlayer defaultPlayer = new JPlayer(1);
        players.add(defaultPlayer);
        playerList.add(defaultPlayer);

        addActionListener(e -> {
            playerList.removeAll(); // Clear the list of players

            int selection = getSelectedIndex() + 1;
            for (int index = 0; index < selection; ++index) {
                JPlayer newPlayer = new JPlayer(index + 1);
                players.add(newPlayer);
                playerList.add(newPlayer);
            }

            playerList.revalidate();
            playerList.repaint();
        });
    }

    /**
     * @return An array of the JPlayers that will participate.
     */
    public JPlayer[] getPlayers() {
        return players.toArray(new JPlayer[0]);
    }
}
