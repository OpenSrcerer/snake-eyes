/*
 * <h3>Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 OpenSrcerer</h3>
 */

package personal.opensrcerer;

import personal.opensrcerer.userInterface.MainWindow;

import javax.swing.*;

/**
 * <h2>A Calculation Multitool</h2>
 *
 * <p>A Java program that fulfills specific requests as required in the Final Course Project Description PDF.<br></p>
 *
 * <h3>Available Calculations:</h3>
 * <ul>
 *     <li>Exact cubes</li>
 *     <li>Factorial of the average</li>
 *     <li>Stats of random integers</li>
 *     <li>Number guessing game</li>
 *     <li>Palindrome detection</li>
 *     <li>Pythagorean triples</li>
 * </ul>
 *
 * <br>
 * <h3>Made for the Final Project in CS105, due December 4th 2020. <br> This work is licensed under the GNU General Public License v3.0</h3>
 * <h3>Note: Special permission has been granted to me by
 * Dr. Alexander Astaras to bypass the project's requirements. <br>
 * Instead of using a text-based menu, I have programmed a GUI.</h3>
 *
 * @author Daniel Stefani (OpenSrcerer)
 * @version 0.0.1
 * @since 2020-11-12
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