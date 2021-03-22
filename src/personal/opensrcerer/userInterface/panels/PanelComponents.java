/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.RunProject;
import personal.opensrcerer.actions.RollRequest;
import personal.opensrcerer.userInterface.MainWindow;
import personal.opensrcerer.util.ButtonType;
import personal.opensrcerer.util.JPlayer;
import personal.opensrcerer.util.RequestDispatcher;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to retrieve custom stylized JComponents
 * to match the ambiance and theme of the UI.
 *
 * Note: Any mention of "custom" in the methods of this class
 * refers to the description above.
 */
public final class PanelComponents {

    // -------- Stylizing Elements --------
    /**
     * Taken from Discord's color theme.
     */
    public static final Color
            discordLightGray = new Color(185, 187, 190),
            discordLessGray = new Color(71, 71, 71),
            discordGray = new Color(54, 57, 63),
            discordGrayer = new Color(47, 49, 54);

    public static final Font bigTitleFont = new Font("Century Gothic", Font.BOLD, 40);
    public static final Font titleFont = new Font("Century Gothic", Font.BOLD, 20);
    public static final Font descriptionFont = new Font("Century Gothic", Font.PLAIN, 14);
    private static final Font actionFont = new Font("Arial", Font.BOLD, 15);
    private static final Font outputFont = new Font("Arial", Font.ITALIC, 13);
    // -------------------------------------------

    // -------- Images --------
    private static ImageIcon[] imagesList;
    // ------------------------

    /**
     * Retrieves a custom JButton.
     * @param buttonName Name and initial text of button.
     * @param type Type of button.
     * @return Customized JButton.
     */
    public static JButton getButton(String buttonName, ButtonType type) {
        JButton button = new JButton();
        button.setHorizontalAlignment(SwingConstants.CENTER);
        setButtonPalette(buttonName, button);
        setMouseListener(button);
        button.addActionListener(getListener(type));
        return button;
    }

    /**
     * Retrieves a custom JButton.
     * @param buttonName Name and initial text of button.
     * @param type Type of button.
     * @param arg Objects to pass to the addActionListener function.
     * @return Customized JButton.
     */
    public static JButton getButton(String buttonName, ButtonType type, Object... arg) {
        JButton button = new JButton();
        setButtonPalette(buttonName, button);
        setMouseListener(button);
        button.addActionListener(getListener(type, button, arg));
        return button;
    }

    /**
     * Retrieves a custom Border.
     * @param borderTitle Title of border.
     * @return Customized Border.
     */
    public static Border getBorder(String borderTitle) {
        Border templateborder = BorderFactory.createLineBorder(discordLightGray, 2, true);
        return BorderFactory.createTitledBorder(templateborder, borderTitle,
                TitledBorder.CENTER, TitledBorder.TOP, descriptionFont, discordLightGray);
    }

    /**
     * Retrieves a custom dashed Border.
     * @return Customized Border.
     */
    public static Border getBorder() {
        return BorderFactory.createDashedBorder(discordLightGray, 2, 5, 5, false);
    }

    /**
     * Changes a given button's theme to match the discord theme.
     * Takes advantage of Swing's HTML support.
     * @param buttonName String to change the button name to.
     * @param button Button to change.
     */
    public static void setButtonPalette(String buttonName, JButton button) {
        button.setText("<html><p style=\"text-align:center\">" + buttonName + "</p></html>");
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBackground(discordGray);
        button.setForeground(discordLightGray);
        button.setFont(actionFont);
    }

    /**
     * A generified function that creates a custom JComboBox.
     * @param selections Number of selections on the JComboBox.
     * @param <W> Type of data that this JComboBox holds.
     * @return A Custom JComboBox.
     */
    public static <W> JComboBox<W> getComboBox(W[] selections) {
        JComboBox<W> box = new JComboBox<>(selections);
        box.setBackground(discordGrayer);
        box.setFont(descriptionFont);
        return box;
    }

    /**
     * A generified function that creates a custom JComboBox.
     * @param selections Number of selections on the JComboBox.
     * @param playerList List of players before starting. (GUI Element)
     * @param <W> Type of data that this JComboBox holds.
     * @return A JComboBox that updates the number of players on the given JPanel player board.
     */
    public static <W> JComboBox<W> getPlayerComboBox(W[] selections, JPanel playerList) {
        JComboBox<W> box = new JComboBox<>(selections);
        box.setBackground(discordGrayer);
        box.setFont(descriptionFont);
        box.addActionListener(e -> {
            playerList.removeAll();
            int selection = box.getSelectedIndex() + 1;
            JPlayer[] players = new JPlayer[selection];

            for (int index = 0; index < selection; ++index) {
                JPlayer newPlayer = new JPlayer(index + 1);
                players[index] = newPlayer;
                playerList.add(newPlayer);
            }

            MainWindow.setPlayers(players);
            playerList.revalidate();
            playerList.repaint();
        });

        return box;
    }

    /**
     * @return A discord-themed JSeperator.
     */
    public static JSeparator getSeparator() {
        JSeparator separator = new JSeparator();
        separator.setBackground(discordGrayer);
        separator.setForeground(discordLightGray);
        return separator;
    }

    /**
     * @return A custom discord-themed JPanel.
     */
    public static JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(discordGrayer);
        return panel;
    }

    /**
     * @param manager A layout manager to attach to the
     *                created JPanel.
     * @return A custom discord-themed JPanel with
     * the given layout manager attached.
     */
    public static JPanel getJPanel(LayoutManager manager) {
        JPanel panel = new JPanel();
        panel.setLayout(manager);
        panel.setBackground(discordGrayer);
        return panel;
    }

    /**
     * @param layout int reperesented by a BoxLayout enum
     *               that shows the alignment of a BoxLayout
     * @return A JPanel with a set BoxLayout manager, with the
     * provided alignment.
     */
    public static JPanel getJPanel(int layout) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, layout));
        panel.setBackground(discordGrayer);
        return panel;
    }

    /**
     * @param defaultText Default placeholder text for JTextField.
     * @param cols Number of columns JTextField will be constructed with.
     * @return A custom themed JTextField.
     */
    public static JTextField getTextField(String defaultText, int cols) {
        JTextField field = new JTextField(defaultText, cols);
        field.setFont(outputFont);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBackground(discordGrayer);
        field.setForeground(discordLightGray);
        field.setCaretColor(discordLightGray);
        setMouseListener(field);
        return field;
    }

    /**
     * @param name Text of JLabel.
     * @param font Font to use with JLabel.
     * @return A custom themed JLabel.
     */
    public static JLabel getLabel(String name, Font font) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setForeground(discordLightGray);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    /**
     * @param imageNumber Number of the image to use.
     * @return A custom JLabel that is constructed with
     * an image instead of text.
     */
    public static JLabel getImageLabel(int imageNumber) {
        if (imageNumber < 0 || imageNumber > imagesList.length - 1) {
            throw new IllegalArgumentException("Invalid image index");
        }
        JLabel picLabel = new JLabel(imagesList[imageNumber]);
        picLabel.setBackground(discordGrayer);
        return picLabel;
    }

    /**
     * @return A custom JLabel that is constructed with
     * a gif instead of text.
     */
    public static JLabel getLogo() {
        JLabel picLabel = new JLabel(imagesList[6]);
        picLabel.setBackground(discordGrayer);
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return picLabel;
    }

    /**
     * @return A custom JLabel that is constructed with
     * a gif instead of text.
     */
    public static JLabel getSkeletonLogo() {
        JLabel picLabel = new JLabel(imagesList[9]);
        picLabel.setBackground(discordGrayer);
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return picLabel;
    }

    /**
     * @return A custom JLabel that is constructed with
     * a png instead of text.
     */
    public static JLabel getSpeakerUnmute() {
        JLabel speaker = new JLabel(imagesList[7]);
        speaker.setBackground(discordGrayer);
        setSpeakerListener(speaker);
        return speaker;
    }

    /**
     * @return A custom JLabel that is constructed with
     * a png instead of text.
     */
    public static JLabel getSpeakerMute() {
        JLabel speaker = new JLabel(imagesList[8]);
        speaker.setBackground(discordGrayer);
        setSpeakerListener(speaker);
        return speaker;
    }

    /**
     * @param playerName Name of player whose turn it is.
     * @return The Dice panel component.
     */
    public static JPanel getDicePanel(String playerName) {
        JLabel die1 = getImageLabel(10),
                die2 = getImageLabel(10),
                die3 = getImageLabel(10),
                die4 = getImageLabel(10);

        JPanel allDies = getJPanel(BoxLayout.PAGE_AXIS);
        JPanel pointDies = getJPanel(BoxLayout.LINE_AXIS);
        JPanel rollDies = getJPanel(BoxLayout.LINE_AXIS);

        pointDies.add(Box.createRigidArea(new Dimension(20, 0))); // Side spacing L
        pointDies.add(die1);
        pointDies.add(Box.createRigidArea(new Dimension(20, 0))); // Inbetween spacing
        pointDies.add(die2);
        pointDies.add(Box.createRigidArea(new Dimension(20, 0))); // Side spacing R

        rollDies.add(die3);
        rollDies.add(Box.createRigidArea(new Dimension(20, 0))); // Inbetween spacing
        rollDies.add(die4);

        allDies.add(Box.createRigidArea(new Dimension(0, 20))); // Top Spacing
        allDies.add(getLabel(playerName + "'s Point Dice: ", titleFont));
        allDies.add(pointDies);
        allDies.add(Box.createRigidArea(new Dimension(0, 20))); // Inbetween spacing
        allDies.add(getLabel(playerName + "'s Current Dice: ", titleFont));
        allDies.add(rollDies);
        allDies.add(Box.createRigidArea(new Dimension(0, 20))); // Bottom Spacing
        allDies.setBorder(getBorder());

        return allDies;
    }

    /**
     * @param round The current round.
     * @return A fully working and modular JPanel scoreboard.
     */
    public static JPanel getScoreboard(int round) {
        JPanel outerScoreboard = getJPanel(BoxLayout.PAGE_AXIS);
        JPanel innerScoreboard = getJPanel(BoxLayout.PAGE_AXIS);
        outerScoreboard.add(Box.createRigidArea(new Dimension(350, 10)));

        List<JPlayer> sortedPlayers = Arrays.stream(MainWindow.getPlayers()).sorted((a, b) -> b.getScore() - a.getScore()).collect(Collectors.toList());
        for (JPlayer player : sortedPlayers) {
            innerScoreboard.add(getLabel(player.getPlayerName() + " - Score: " + player.getScore(), titleFont));
            innerScoreboard.add(Box.createRigidArea(new Dimension(200, 10)));
        }
        innerScoreboard.add(Box.createRigidArea(new Dimension(0, 300 - (MainWindow.getPlayers().length * 37))));
        innerScoreboard.setBorder(getBorder("Scoreboard // Round " + round));
        outerScoreboard.add(innerScoreboard);
        outerScoreboard.add(Box.createRigidArea(new Dimension(0, 20)));
        return outerScoreboard;
    }

    public static JSlider getSlider() {
        JSlider slider = new JSlider();
        slider.setMaximum(100);
        slider.setMinimum(0);
        slider.setValue(100);
        slider.setExtent(2);
        slider.setBackground(discordGrayer);
        slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        slider.setPreferredSize(new Dimension(50, 25));

        slider.addChangeListener(e -> {
            if (slider.getValueIsAdjusting()) {
                return;
            }

            float volume = slider.getValue() / 100f;

            if (volume == 0f) {
                JPanel musicPanel = MainWindow.getMusicPanel();
                musicPanel.removeAll();
                musicPanel.add(slider);
                // TODO bug with disappearing mute button
                musicPanel.revalidate();
                musicPanel.repaint();
            } else if (volume > 0f & MainWindow.isMute()) {
                JPanel musicPanel = MainWindow.getMusicPanel();
                musicPanel.removeAll();
                musicPanel.add(slider);
                musicPanel.add(PanelComponents.getSpeakerUnmute());
                musicPanel.revalidate();
                musicPanel.repaint();
            }

            MainWindow.setVolume(volume);
        });

        return slider;
    }

    /**
     * @param buttonName Name of button.
     * @param type Type of button. See ButtonType.
     * @see ButtonType
     * @return A custom button inside a JPanel that has a border.
     */
    public static JPanel getBorderedButton(String buttonName, ButtonType type) {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JButton button = getButton(buttonName, type);
        button.setFocusPainted(false);
        panel.add(button);
        panel.setBorder(BorderFactory.createLineBorder(discordLessGray, 1));
        return panel;
    }

    /**
     * @see ButtonType
     * @return A custom button inside a JPanel that has a border.
     */
    public static JPanel getRollButton() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JButton button = new JButton(imagesList[11]);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        setButtonPalette("", button);
        setMouseListener(button);
        button.addActionListener(getListener(ButtonType.ROLL));
        button.setFocusPainted(false);
        panel.add(button);
        panel.setBorder(BorderFactory.createLineBorder(discordLessGray, 1));
        return panel;
    }

    /**
     * @param round Current round.
     * @param playerName The player whose turn it is.
     * @return The bottom panel containing volume controls and other info.
     */
    public static JPanel getTopPanel(int round, String playerName) {
        JPanel bottomPanel = PanelComponents.getJPanel();
        bottomPanel.add(getLabel("Round " + round + " // Roll, " + playerName + "!", bigTitleFont), BorderLayout.CENTER);
        return bottomPanel;
    }

    /**
     * @return The bottom panel containing volume controls and other info.
     */
    public static JPanel getBottomPanel() {
        JPanel bottomPanel = PanelComponents.getJPanel(new BorderLayout());
        bottomPanel.add(MainWindow.getMusicPanel(), BorderLayout.EAST);
        bottomPanel.add(PanelComponents.getLabel("  v0.0.1", descriptionFont), BorderLayout.WEST);
        return bottomPanel;
    }

    /**
     * @param name Text attached to set box.
     * @param isSelected Whether the checkbox is originally
     *                   checked or not.
     * @return A custom checkbox.
     */
    public static JCheckBox getCheckBox(String name, boolean isSelected) {
        JCheckBox box = new JCheckBox(name, isSelected);
        box.setBackground(discordGray);
        box.setFont(actionFont);
        box.setForeground(discordLightGray);
        return box;
    }

    /**
     * Initializes all images retrieved from the resources project dir and dumps them into an array for later usage.
     * @throws NullPointerException If retrieved image is null.
     */
    public static void initializeImages() {
        imagesList = new ImageIcon[12];
        for (int index = 0; index < 6; ++index) {
            // Retrieve a resource stream using a base class as a reference point.
            imagesList[index] = new ImageIcon(RunProject.class.getResource("/resources/die" + (index + 1) + ".png"));
        }
        imagesList[6] = new ImageIcon(RunProject.class.getResource("/resources/logo.png"));
        imagesList[7] = new ImageIcon(RunProject.class.getResource("/resources/speaker32.png"));
        imagesList[8] = new ImageIcon(RunProject.class.getResource("/resources/speaker32m.png"));
        imagesList[9] = new ImageIcon(RunProject.class.getResource("/resources/skelly.png"));
        imagesList[10] = new ImageIcon(RunProject.class.getResource("/resources/dieq.png"));
        imagesList[11] = new ImageIcon(RunProject.class.getResource("/resources/flamingdice.png"));
    }

    /**
     * Function that sets a mouse listener to a component,
     * that changes its background when mouse enters and exits
     * it.
     * @param component The button to add a listener to.
     */
    public static void setMouseListener(JComponent component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                component.setBackground(discordLessGray);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getDefaultCursor());
                component.setBackground(discordGray);
            }
        });
    }

    /**
     * Creates a listener aimed at a target field that changes
     * the cursor to a text cursor whenever this target
     * field is entered.
     * @param field Target field.
     */
    public static void setMouseListener(JTextField field) {
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    /**
     * Function that sets a mouse listener to speaker labels.
     * @param component The button to add a listener to.
     */
    public static void setSpeakerListener(JComponent component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent evt) {
                JPanel musicPanel = MainWindow.getMusicPanel();
                JSlider slider = PanelComponents.getSlider();

                musicPanel.removeAll();
                if (MainWindow.isMute()) {
                    MainWindow.setVolume(0.25f);
                    slider.setValue(25);
                    musicPanel.add(slider);
                    musicPanel.add(PanelComponents.getSpeakerUnmute());
                } else {
                    MainWindow.setVolume(0f);
                    slider.setValue(0);
                    musicPanel.add(slider);
                    musicPanel.add(PanelComponents.getSpeakerMute());
                }
                musicPanel.revalidate();
                musicPanel.repaint();
            }
        });
    }

    /**
     * Retrieves a listener according to what a specific button should do when clicked.
     * Used for GUI switch listeners.
     * @param type Type of button.
     * @return An ActionListener according to the button type.
     */
    private static ActionListener getListener(ButtonType type) {
        // Variable "e" indicates a new lambda action listener
        // FORMAT:
        //        Remove elements from MainWindow WindowPane
        //        Reset user cursor
        //        Set specific components from the panels package
        //        Pack JFrame & Repaint

        return switch (type) {
            case HELP, CREDITS, ROLL -> e -> {
                // TODO
                MainWindow.updateJFrame();
            };
            case START -> e -> {
                MainWindow.getWindowPane().removeAll();
                MainWindow.getWindowPane().setCursor(Cursor.getDefaultCursor());
                Game.setComponents(MainWindow.getWindowPane());
                MainWindow.updateJFrame();
            };
            case EXIT -> e -> {
                    MainWindow.disposeJFrame();
                    RequestDispatcher.killExecutor();
            };
        };
    }

    /**
     * Retrieves a listener according to what a specific button should do when clicked.
     * Used for GUI request listeners.
     * @param type Type of button
     * @param button Target button
     * @param args Any further arguments that the request may require.
     * @return Specific ActionListener
     */
    private static ActionListener getListener(ButtonType type, JButton button, Object[] args) {

        // Variable "e" indicates a new lambda action listener
        // FORMAT:
        //        Create new specific request (args)

        // testing
        return switch (type) {
            case ROLL -> e -> new RollRequest((JTextArea) args[0], button, (JTextField[]) args[1]);
            default -> throw new IllegalArgumentException("Invalid Button Listener");
        };
    }
}
