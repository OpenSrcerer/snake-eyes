package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.SnakeEyes;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * The JPanel that contains every UI element that will be shown to the user while in-game.
 */
public class GamePanel {

    /**
     * Set all the components of the given ContentPane to the GamePanel components.
     * @param pane ContentPane of MainWindow's JFrame.
     */
    public static void setComponents(final Container pane) {
        final JPanel totalPanel = getJPanel(BoxLayout.PAGE_AXIS),
                scoreboardPanel = getJPanel(BoxLayout.PAGE_AXIS),
                gamePanelSep = getJPanel(BoxLayout.PAGE_AXIS),
                wholeSep = getJPanel(BoxLayout.LINE_AXIS),
                gridPanel = getJPanel(new GridLayout(1, 3));

        JSeparator sep = getSeparator();
        sep.setOrientation(JSeparator.VERTICAL);
        JSeparator sep2 = getSeparator();
        sep2.setMaximumSize(new Dimension(350, 1));
        sep2.setPreferredSize(new Dimension(350, 1));

        // Left side of panel, scoreboard
        scoreboardPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        scoreboardPanel.add(getSkeletonLogo());
        scoreboardPanel.add(Box.createRigidArea(new Dimension(0, 36)));
        scoreboardPanel.add(SnakeEyes.getScoreboard());

        gamePanelSep.add(Box.createRigidArea(new Dimension(0, 20)));
        gamePanelSep.add(SnakeEyes.getDiceboard());
        gamePanelSep.add(Box.createRigidArea(new Dimension(0, 20)));
        gamePanelSep.add(sep2);
        gamePanelSep.add(SnakeEyes.getRollButton());

        wholeSep.add(sep);
        wholeSep.add(gamePanelSep);

        gridPanel.add(scoreboardPanel);
        gridPanel.add(wholeSep);

        totalPanel.add(SnakeEyes.getBanner());
        totalPanel.add(getSeparator());
        totalPanel.add(gridPanel);
        totalPanel.add(getSeparator());
        totalPanel.add(getBottomPanel());

        pane.add(totalPanel);
    }
}