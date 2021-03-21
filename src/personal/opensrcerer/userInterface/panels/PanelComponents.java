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
import personal.opensrcerer.util.RequestDispatcher;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

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
        Border templateborder = BorderFactory.createLineBorder(discordLightGray, 3, true);
        return BorderFactory.createTitledBorder(templateborder, borderTitle,
                TitledBorder.CENTER, TitledBorder.BOTTOM, descriptionFont, discordLightGray);
    }

    /**
     * Changes a given button's theme to match the discord theme.
     * Takes advantage of Swing's HTML support.
     * @param buttonName String to change the button name to.
     * @param button Button to change.
     */
    public static void setButtonPalette(String buttonName, JButton button) {
        button.setText("<html>" + buttonName + "</html>");
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBackground(discordGray);
        button.setForeground(discordLightGray);
        button.setFont(actionFont);
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
     * @param rows Number of rows JTextArea will be constructed with.
     * @param cols Number of columns JTextArea will be constructed with.
     * @return A custom themed JTextArea.
     */
    public static JTextArea getTextArea(int rows, int cols) {
        JTextArea area = new JTextArea(rows, cols);
        area.setFont(outputFont);
        area.setBackground(discordGrayer);
        area.setForeground(discordLightGray);
        area.setPreferredSize(new Dimension(200, 200));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        return area;
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
        if (imageNumber < 0 || imageNumber > 6) {
            throw new IllegalArgumentException("Invalid image");
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
    public static JLabel getSpeakerUnmute() {
        JLabel picLabel = new JLabel(imagesList[7]);
        picLabel.setBackground(discordGrayer);
        return picLabel;
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
     * @param interactionHolder JPanel that represents
     *                          the area that the user
     *                          interacts with.
     * @param outputArea JTextArea where the output of
     *                   the calculations will go.
     * @return A JSplitPane comprising of the interactive part
     * of the UI, and the output part of the UI.
     */
    public static JSplitPane getSplitPane(JPanel interactionHolder, JTextArea outputArea) {
        // Create a scroll pane as holder for the output
        final JPanel scrollHolder = getJPanel(new GridLayout());
        final JScrollPane scroll = new JScrollPane(
                outputArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scroll.setBackground(discordGray);
        scrollHolder.add(scroll);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                interactionHolder, scrollHolder
        );
        splitPane.setEnabled(false);
        splitPane.setDividerSize(1);

        return splitPane;
    }

    /**
     * Initializes all images retrieved from the resources project dir and dumps them into an array for later usage.
     * @throws NullPointerException If retrieved image is null.
     */
    public static void initializeImages() {
        imagesList = new ImageIcon[8];
        for (int index = 0; index < 6; ++index) {
            // Retrieve a resource stream using a base class as a reference point.
            imagesList[index] = new ImageIcon(RunProject.class.getResource("/resources/die" + (index + 1) + ".png"));
        }

        imagesList[6] = new ImageIcon(RunProject.class.getResource("/resources/logo.png"));
        imagesList[7] = new ImageIcon(RunProject.class.getResource("/resources/speaker32.png"));
    }

    /**
     * Function that sets a mouse listener to a button,
     * that changes its background when mouse enters and exits
     * it.
     * @param button The button to add a listener to.
     */
    public static void setMouseListener(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                button.setBackground(discordLessGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getDefaultCursor());
                button.setBackground(discordGray);
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getDefaultCursor());
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
            case HELP, CREDITS -> e -> {
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
            default -> throw new IllegalArgumentException("Invalid button type provided");
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
