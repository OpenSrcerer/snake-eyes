/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.util.ButtonType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * Manages the Credits UI Menu.
 */
public abstract class CreditsPanel {

    /**
     * The final panel for the credits components.
     */
    private static final JPanel creditsPanel;

    /**
     * Bottom panel instance.
     */
    private static final BottomPanel bottomPanel;

    static {
        final JPanel infoPanel = getJPanel(BoxLayout.PAGE_AXIS);
        final JPanel imagePanel = getJPanel();
        final JPanel buttonPanel = getJPanel();
        creditsPanel = getJPanel(BoxLayout.PAGE_AXIS);
        bottomPanel = new BottomPanel();

        imagePanel.add(getImageLabel(1));
        buttonPanel.add(getButton("Back", ButtonType.BACK));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(getLabel("Snake Eyes Game v0.0.1", titleFont));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(getLabel("GNU © 2021 Daniel Stefani / OpenSrcerer", descriptionFont));
        infoPanel.add(getLabel("Made for my final CS106 Project", descriptionFont));
        infoPanel.add(getLabel("Thank you Bonkers & Marce", descriptionFont));
        infoPanel.add(buttonPanel);

        creditsPanel.add(imagePanel);
        creditsPanel.add(infoPanel);
        creditsPanel.add(getSeparator());
        creditsPanel.add(bottomPanel);

        creditsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    /**
     * Set specific components to a target ContentPane.
     * @param pane Target ContentPane.
     */
    public static void setComponents(final Container pane) {
        pane.add(creditsPanel);
        bottomPanel.refresh();
    }
}