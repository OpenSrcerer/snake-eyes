/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface;

import personal.opensrcerer.RunProject;
import personal.opensrcerer.userInterface.panels.PanelComponents;
import personal.opensrcerer.userInterface.panels.Start;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

/**
 * The main and only JFrame that the GUI uses.
 */
public final class MainWindow extends JFrame {
    /**
     * Main window where everything occurs! Only created once.
     */
    private static final MainWindow window = new MainWindow();

    private MainWindow() {
        super("The One and Only Snake Eyes Game");
        try {
            PanelComponents.initializeImages();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up the content pane.
        Start.setComponents(window.getContentPane());
        // Pack the window so that the components
        // get their preferred size assigned.
        updateJFrame();
        // Make window non-resizable
        window.setResizable(false);
        // Display the window.
        window.setVisible(true);

        /*
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(RunProject.class.getResource("/resources/music.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Integer.MAX_VALUE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    /**
     * @return The content pane for this JFrame.
     */
    public static Container getWindowPane() {
        return window.getContentPane();
    }

    /**
     * Packs, revalidates and repaints the JFrame to correctly fit a new layout.
     */
    public static void updateJFrame() {
        window.pack(); // Fit size of JFrame
        window.revalidate(); // Mark JFrame as "dirty"
        window.repaint();
        window.setLocationRelativeTo(null); // Center window
    }

    /**
     * Dispose of the window and exit the program.
     */
    public static void disposeJFrame() {
        window.dispose();
    }
}
