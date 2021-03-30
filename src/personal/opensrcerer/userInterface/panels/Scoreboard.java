package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.Player;
import personal.opensrcerer.util.SnakeEyes;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class Scoreboard extends JPanel {

    private final JPanel innerScoreboard;

    /**
     * Returns a modular JPanel scoreboard.
     */
    public Scoreboard() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(discordGrayer);

        innerScoreboard = getJPanel(BoxLayout.PAGE_AXIS);
        updateScoreboard();
        innerScoreboard.setBorder(PanelComponents.getBorder("Scoreboard // Round " + SnakeEyes.getCurrentRound()));

        add(innerScoreboard);
    }

    /**
     * Update changes on this scoreboard and set the cursor position on a specific player.
     */
    public void refresh() {
        innerScoreboard.removeAll();
        updateScoreboard();
        innerScoreboard.revalidate();
        innerScoreboard.repaint();
    }

    /**
     * Add the proper elements to this scoreboard.
     */
    private void updateScoreboard() {
        innerScoreboard.removeAll();

        // For each loop used in a Stream
        SnakeEyes.getPlayers()
            .sorted(Comparator.comparing(Player::getPlayerName)) // Sort the Stream alphabetically depending on the player name
            .forEach(player -> { // Refresh every name on the list
            JPanel playerCursorPanel = getJPanel();
            if (player.equals(SnakeEyes.getPlayerOnTurn())) { // Add the cursor on the player whose turn it is
                playerCursorPanel.add(PanelComponents.getCursor());
            }
            playerCursorPanel.add(getLabel(player.getPlayerName() + " - Score: " + player.getScore(), titleFont));
            // TODO fix alignment
            innerScoreboard.add(playerCursorPanel);
        });

        innerScoreboard.add(Box.createRigidArea(new Dimension(0, 300 - (SnakeEyes.size() * 37))));
    }
}
