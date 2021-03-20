/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.actions;

import javax.swing.*;

/**
 * This interface has the purpose of making it easy
 * for the processing queue to handle every action
 * taken.
 */
public interface Request extends Runnable {

    /**
     * Toggles the state of a JButton to clickable or not (run buttons primarily).
     * @param button Target button.
     */
    default void toggleRunButton(JButton button) {
        SwingUtilities.invokeLater(() -> button.setEnabled(!button.isEnabled()));
    }

    /**
     * Gives a JProgressBar a new value.
     * @param bar Target JProgressBar
     * @param value Value to set
     */
    default void updateProgressBar(JProgressBar bar, int value) {
        SwingUtilities.invokeLater(() -> bar.setValue(value));
    }

    /**
     * Updates the output area with the preset text.
     * Scrolls to the bottom using the caret.
     * @param outputArea Target output JTextArea.
     * @param areaText Text to output in the area.
     * @param areaRows The number of newlines in the given text.
     */
    default void updateOutputArea(JTextArea outputArea, String areaText, int areaRows) {
        // set field rows to fit in result exactly
        SwingUtilities.invokeLater(() -> {
            outputArea.setRows(areaRows);
            outputArea.setText(areaText);
            outputArea.setCaretPosition(outputArea.getDocument().getLength() - 1);
        });
    }
}
