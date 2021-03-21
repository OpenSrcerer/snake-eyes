/*
 * <h3>Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 OpenSrcerer</h3>
 */

package personal.opensrcerer;

import personal.opensrcerer.userInterface.MainWindow;

import javax.swing.*;

/**
 * <h2>Snake Eyes Game</h2>
 *
 * <p>A Java program that fulfills specific requests as required in the Final Course Project Description PDF.<br></p>
 * <br>
 * <h3>Made for the Final Project in CS106, due April 1st 2021. <br> This work is licensed under the GNU General Public License v3.0</h3>
 *
 * @author Daniel Stefani (OpenSrcerer)
 * @version 0.0.1
 * @since 2020-03-20
 */

public class RunProject {

    /**
     * flag to check if look and feel change was successful
    */
    private static boolean lookAndFeelSuccessful = false;

    /**
     * Start the program's execution.
     * @param args Arguments taken in if the program was started from console.
     * @throws UnsupportedLookAndFeelException If neither of the look and feel
     * types are supported.
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // Try to set the UI look and feel to match the system
        setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        if (!lookAndFeelSuccessful)
            // Revert to a cross platform version
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        if (!lookAndFeelSuccessful)
            throw new UnsupportedLookAndFeelException("Program is unable to start, provided look and feels are incompatible!");

        // Invoke managing of the UI to a specific UI management thread
        SwingUtilities.invokeLater(MainWindow::createAndShowGUI);
    }

    /**
     * Set the look and feel of the system with error handling.
     * @param lfClassName Look and Feel Class Name
     */
    private static void setLookAndFeel(String lfClassName) {
        try {
            UIManager.setLookAndFeel(lfClassName);
            lookAndFeelSuccessful = true;
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException ex) {
            System.out.println("Look and feel is unsupported.");
        }
    }
}