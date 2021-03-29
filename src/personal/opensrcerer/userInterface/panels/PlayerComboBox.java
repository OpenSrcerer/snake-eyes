package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.Player;

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
    private final List<Player> players = new ArrayList<>();

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
        Player defaultPlayer = new Player(1);
        players.add(defaultPlayer);
        playerList.add(defaultPlayer);

        addActionListener(e -> {
            playerList.removeAll(); // Clear the JPanel
            players.clear(); // Clear the list of Players

            int selection = getSelectedIndex() + 1;
            for (int index = 0; index < selection; ++index) {
                Player newPlayer = new Player(index + 1);
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
    public Player[] getPlayers() {
        return players.toArray(new Player[0]);
    }
}
