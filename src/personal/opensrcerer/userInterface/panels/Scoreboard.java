package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.Player;
import personal.opensrcerer.util.SnakeEyes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

public class Scoreboard extends JPanel {

    /**
     * Returns a modular JPanel scoreboard.
     */
    public Scoreboard() {
        super();
        styleScoreboard(1);
    }

    /**
     * Add the proper elements to this scoreboard.
     */
    public void styleScoreboard(int cursorPosition) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(discordGrayer);

        JPanel innerScoreboard = getJPanel(BoxLayout.PAGE_AXIS);
        add(Box.createRigidArea(new Dimension(350, 10)));

        List<Player> sortedPlayers = SnakeEyes.getPlayers().sorted((a, b) -> b.getScore() - a.getScore()).collect(Collectors.toList());
        for (int index = 0; index < sortedPlayers.size(); ++index) {
            Player player = sortedPlayers.get(index);
            JPanel playerCursorPanel = getJPanel();
            if (index == cursorPosition - 1) { // Condition to add the cursor on the right player
                playerCursorPanel.add(PanelComponents.getCursor());
            }
            playerCursorPanel.add(getLabel(player.getPlayerName() + " - Score: " + player.getScore(), titleFont));
            // TODO fix alignment
            innerScoreboard.add(playerCursorPanel);
        }

        innerScoreboard.add(Box.createRigidArea(new Dimension(0, 300 - (SnakeEyes.size() * 37))));
        innerScoreboard.setBorder(PanelComponents.getBorder("Scoreboard // Round " + SnakeEyes.getCurrentRound()));

        add(innerScoreboard);
    }

    /**
     * Update changes on this scoreboard and set the cursor position on a specific player.
     * @param cursorPosition Position of cursor on the scoreboard.
     */
    public void refresh(int cursorPosition) {
        styleScoreboard(cursorPosition);
        revalidate();
        repaint();
    }
}
