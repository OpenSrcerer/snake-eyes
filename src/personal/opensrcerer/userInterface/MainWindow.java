/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface;

import personal.opensrcerer.userInterface.panels.Night;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The main and only JFrame that the GUI uses.
 */
public final class MainWindow extends JFrame {

    /**
     * Main window where everything occurs! Only created once.
     */
    private static final MainWindow window = new MainWindow();

    int diceroll = 0;
    int dice1, dice2;
    int sum = 0;
    int result=0;
    private JLabel lab1;
    private JButton about;
    private JButton start;
    private JButton reroll;
    private JLabel jdice1;
    private JLabel jdice2;
    private JLabel rdice1;
    private JLabel rdice2;
    private JLabel message;
    private JLabel dicer;

    private MainWindow() {
        super("Snake Eyes Game Spring Project");
        setLayout(new FlowLayout());

        try {
            lab1 = new JLabel("WELCOME TO SNAKE EYES GAME!!!");
            lab1.setToolTipText("Hello there! This is my submission for Spring CS Project!");
            add(lab1);
            about = new JButton("About Section");
            add(about);
            Icon d = new ImageIcon(PanelComponents.getImage("bonk.png"));
            start = new JButton("Start");
            add(start);

            reroll = new JButton(d);
            reroll.setToolTipText("REROLL!");
            add(reroll);

            Icon image = new ImageIcon(PanelComponents.getImage("bonk.png"));
            Icon image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
            jdice1 = new JLabel(image);
            jdice2 = new JLabel(image2);
            add(jdice1);
            add(jdice2);

            rdice1 = new JLabel();
            rdice2 = new JLabel();

            add(jdice1);
            add(jdice2);
            add(rdice1);
            add(rdice2);

            message = new JLabel("");
            add(message);
            dicer = new JLabel("");
            add(dicer);
            EventHandler handle = new EventHandler();
            about.addActionListener(handle);
            start.addActionListener(handle);
            reroll.addActionListener(handle);
        } catch (IOException | NullPointerException ignored) {
            ignored.printStackTrace();
        }

        try {
            setIconImage(PanelComponents.getImage("bonk.png"));
        } catch (IOException | NullPointerException ignored) {
            // If new icon cannot be found, fall back to default
        }

        setResizable(false);
    }

    private class EventHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                if (event.getSource() == about) {
                    JOptionPane.showMessageDialog(null, String.format("Author:ONI", event.getActionCommand()));
                } else if (event.getSource() == start) {

                    dice1 = (int) (Math.random() * (6 - 1 + 1) + 1);


                    Icon image = null, image2 = null;
                    if (dice1 == 1) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 2) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 3) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 4) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 5) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 6) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    }

                    dice2 = (int) (Math.random() * (6 - 1 + 1) + 1);

                    if (dice2 == 1) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 2) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 3) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 4) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 5) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 6) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    }

                    jdice1.setIcon(image);
                    jdice2.setIcon(image2);

                    sum = dice1 + dice2;

                    if (sum == 7 || sum == 11) {
                        message.setText("You Win!");
                        reroll.setEnabled(false);
                    } else if (sum == 2 || sum == 3 || sum == 12) {
                        message.setText("You Lose!");
                        reroll.setEnabled(false);
                    } else {
                        message.setText("Reroll!");
                        rdice1.setIcon(image);
                        rdice2.setIcon(image2);
                    }

                    result = sum;
                    start.setEnabled(false);
                }


                if (event.getSource() == reroll) {


                    dice1 = (int) (Math.random() * (6 - 1 + 1) + 1);

                    Icon image = null, image2 = null;

                    if (dice1 == 1) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 2) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 3) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 4) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 5) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice1 == 6) {
                        image = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    }

                    dice2 = (int) (Math.random() * (6 - 1 + 1) + 1);

                    if (dice2 == 1) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 2) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 3) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 4) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 5) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    } else if (dice2 == 6) {
                        image2 = new ImageIcon(PanelComponents.getImage("bonk.png"));
                    }

                    jdice1.setIcon(image);
                    jdice2.setIcon(image2);
                    sum = dice1 + dice2;
                    if (sum == 7) {
                        message.setText("You Lose!7");
                        reroll.setEnabled(false);
                    } else if (sum == result) {
                        message.setText("You win!R");
                        reroll.setEnabled(false);
                    } else {
                        rdice1.setIcon(image);
                        rdice2.setIcon(image2);
                        result = sum;
                    }
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        window.setSize(500,500);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up the content pane.
        Night.setComponents(window.getContentPane());
        // Pack the window so that the components
        // get their preferred size assigned.
        window.pack();
        // Display the window.
        window.setVisible(true);
    }

    /**
     * @return The content pane for this JFrame.
     */
    public static Container getWindowPane() {
        return window.getContentPane();
    }

    /**
     * Packs JFrame so that every element fits to
     * its preferred size.
     */
    public static void packJFrame() {
        window.pack();
    }

    /**
     * Repaints JFrame for every component in it to reload.
     */
    public static void repaintJFrame() {
        window.repaint();
    }

    /**
     * Dispose of the window and exit the program.
     */
    public static void disposeJFrame() {
        window.dispose();
    }
}
