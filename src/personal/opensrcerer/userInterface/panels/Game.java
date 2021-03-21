package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.RunProject;
import personal.opensrcerer.util.ButtonType;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Game {
    public static void setComponents(final Container pane) {
        JPanel totalLayout = PanelComponents.getJPanel(new GridLayout(1, 2));
        JPanel userPanel = PanelComponents.getJPanel(BoxLayout.Y_AXIS);
        JPanel buttonPanel = PanelComponents.getJPanel(BoxLayout.Y_AXIS);
        JPanel diceSplitPanel = PanelComponents.getJPanel(BoxLayout.PAGE_AXIS);
        JPanel playPanel = PanelComponents.getJPanel();
        JPanel rollPanel = PanelComponents.getJPanel();
        JPanel dicePanel1 = PanelComponents.getJPanel();
        JPanel dicePanel2 = PanelComponents.getJPanel();

        JLabel die1 = PanelComponents.getImageLabel(1),
                die2 = PanelComponents.getImageLabel(2),
                die3 = PanelComponents.getImageLabel(3),
                die4 = PanelComponents.getImageLabel(4),
                result = PanelComponents.getLabel("you are a loser.", PanelComponents.descriptionFont);
        JButton playButton = PanelComponents.getButton("lose all money", ButtonType.HELP),
                rollButton = PanelComponents.getButton("lose all money but later", ButtonType.HELP);

        Border diceBorder = PanelComponents.getBorder("gambling is bad. stop."),
                resultBorder = PanelComponents.getBorder("Result");
        result.setBorder(resultBorder);

        dicePanel1.add(die1);
        dicePanel1.add(die2);
        dicePanel1.setBorder(diceBorder);

        dicePanel2.add(die3);
        dicePanel2.add(die4);

        diceSplitPanel.add(dicePanel1);
        diceSplitPanel.add(dicePanel2);

        playPanel.add(playButton);
        rollPanel.add(rollButton);

        buttonPanel.add(playPanel);
        buttonPanel.add(rollPanel);

        userPanel.add(buttonPanel);
        userPanel.add(result);
        userPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        totalLayout.add(diceSplitPanel);
        totalLayout.add(userPanel);

        pane.add(totalLayout);
    }
}
