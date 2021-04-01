/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface;

import personal.opensrcerer.RunProject;
import personal.opensrcerer.userInterface.panels.PanelComponents;
import personal.opensrcerer.userInterface.panels.StartPanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;

/**
 * The main and only JFrame that the GUI uses.
 */
public final class MainWindow extends JFrame {
    /**
     * Main window where everything occurs! Only created once.
     */
    private static MainWindow window;

    /**
     * The Audio Clip that loops in the program.
     */
    private static Clip clip;

    public MainWindow() {
        super("The One and Only Snake Eyes Game");

        clip = null;
        try {
            // Initialize Audio
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(RunProject.class.getResource("/resources/highrollers.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            // Initialize used images
            PanelComponents.initializeImages();
            // Set singleton item
            window = this;
            // Show the GUI
            createAndShowGUI();
        } catch (Exception ex) {
            System.out.println("Something went wrong! " + ex.getMessage());
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up the content pane.
        StartPanel.setComponents(getContentPane());
        // Pack the window so that the components
        // get their preferred size assigned.
        updateJFrame();
        // Make window non-resizable
        setResizable(false);
        // Display the window.
        setVisible(true);
        // Start and loop the music
        clip.start();
        clip.loop(Integer.MAX_VALUE);
    }

    /**
     * Sets the volume of the playing clip - first turning the volume from a linear to a logarithmic scale.
     * @param volume The linear amount of volume to use.
     */
    public static void setVolume(float volume) {
        // FloatControl uses a logarithmic amplitude! Corresponding linear multiplier:
        // pow(10.0, gainDB/20.0)
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue((float) Math.log10(volume) * 20f); // Linear applied in reverse
    }

    /**
     * @return Whether the clip file is currently muted.
     */
    public static boolean isMute() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return ((float) Math.pow(10f, gainControl.getValue() / 20f)) <= 1.0E-3f;
    }

    /**
     * @return The content pane for the singleton Window JFrame.
     */
    public static Container getWindowPane() {
        return window.getContentPane();
    }

    /**
     * Packs, revalidates and repaints the singleton Window JFrame to correctly fit a new layout.
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