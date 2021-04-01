/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.Player;
import personal.opensrcerer.util.SnakeEyes;

import javax.swing.*;

import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class Scoreboard extends JPanel {

    public static final class ScoreboardPlayer extends JPanel {
        /**
         * The Player object that contains data for this ScoreboardPlayer object.
         */
        private final Player player;

        /**
         * The Label that shows the player's information.
         */
        private final JLabel playerInfo;

        /**
         * The turn cursor.
         */
        private final JLabel cursor;

        private ScoreboardPlayer(Player player) {
            super();
            setBackground(discordGrayer);
            this.player = player;
            this.playerInfo = getLabel(player.getPlayerName() + " - Score: " + player.getScore(), titleFont);
            this.cursor = PanelComponents.getCursor();
            add(cursor);
            add(playerInfo);
            refresh();
        }

        /**
         * Refresh the information for the player and make the cursor visible if it's the player's turn.
         */
        public void refresh() {
            // Make the cursor visible the player whose turn it is
            cursor.setVisible(player.equals(SnakeEyes.getPlayerOnTurn()));
            // Update the player's information
            playerInfo.setText(player.getPlayerName() + " - Score: " + player.getScore());
            // Refresh the information
            revalidate();
            repaint();
        }
    }

    /**
     * Contains all the ScoreboardPlayers to update.
     */
    private final ScoreboardPlayer[] scoreboardPlayers;

    /**
     * Returns a modular JPanel scoreboard.
     */
    public Scoreboard() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(discordGrayer);

        scoreboardPlayers = SnakeEyes.getPlayers()
                .map(ScoreboardPlayer::new) // Change the stream type by making new ScoreboardPlayers
                .toArray(ScoreboardPlayer[]::new); // Box the stream into an array

        // Add every ScoreboardPlayer
        for (ScoreboardPlayer player : scoreboardPlayers) {
            add(player);
        }
        // Add RigidArea of a predefined size
        add(Box.createRigidArea(new Dimension(0, 329 - (SnakeEyes.size() * 37))));

        setBorder(PanelComponents.getBorder("Scoreboard // Round " + SnakeEyes.getCurrentRound()));
        // TODO UPDATE FLICKERING PROBLEM
    }

    /**
     * Update changes on this scoreboard and set the cursor position on a specific player.
     */
    public void refresh() {
        for (ScoreboardPlayer player : scoreboardPlayers) {
            player.refresh(); // Refresh every player's label and cursor
        }

        // Update border
        setBorder(PanelComponents.getBorder("Scoreboard // Round " + SnakeEyes.getCurrentRound()));
    }
}
