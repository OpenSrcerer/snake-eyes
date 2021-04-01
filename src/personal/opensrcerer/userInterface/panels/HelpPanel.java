/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.ButtonType;

import javax.swing.*;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * Holds the components for the Help UI menu.
 */
public final class HelpPanel {

    /**
     * The final panel for the help components.
     */
    private static final JPanel helpPanel;

    /**
     * Bottom panel instance.
     */
    private static final BottomPanel bottomPanel;

    static {
        final JPanel infoPanel = getJPanel(BoxLayout.PAGE_AXIS);
        final JPanel buttonPanel = getJPanel();
        helpPanel = getJPanel();
        bottomPanel = new BottomPanel();

        buttonPanel.add(getButton("Back", ButtonType.BACK));

        JLabel helpLabel = getImageLabel(14);
        helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(helpLabel);
        infoPanel.add(buttonPanel);
        infoPanel.add(getSeparator());
        infoPanel.add(bottomPanel);

        helpPanel.add(infoPanel);
    }

    /**
     * Set specific components to a target ContentPane.
     * @param pane Target ContentPane.
     */
    public static void setComponents(final Container pane) {
        pane.add(helpPanel);
        bottomPanel.refresh();
    }
}
