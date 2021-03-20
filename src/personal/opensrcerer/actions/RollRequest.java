/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.actions;

import personal.opensrcerer.managers.RequestManager;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Menu option 2: Factorial of the average. Ask the user to input 5 positive integers between 0
 * and 15. Proceed to calculate and display the average of the 5 integers, as well as the factorial of
 * the integer in the group which is closest to the group's average. You may need to look up the
 * factorial formula in a mathematics book. You should build the code for the calculation of the
 * factorial rather than use a built-in Java function.
 */
public class RollRequest implements Request {

    /**
     * Array that contains 5 integers input from the user.
     */
    private final int[] numbers = new int[5];

    /**
     * Output area for the program's output.
     */
    private final JTextArea outputArea;

    /**
     * Button that starts the execution of this request.
     */
    private final JButton button;

    /**
     * Create a new FactorialRequest object and put it in the
     * Request queue.
     * @param outputArea Output area for the program's output.
     * @param button Button that starts the execution of this request.
     * @param fields TextFields to parse the input from.
     */
    public RollRequest(JTextArea outputArea, JButton button, JTextField[] fields) {
        this.outputArea = outputArea;
        this.button = button;

        for (int index = 0; index < 5; ++index) {
            try {
                numbers[index] = Integer.parseInt(fields[index].getText());
                if (numbers[index] < 0 || numbers[index] > 15)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                updateOutputArea(outputArea, outputArea.getText() + "Error in input: Insert an integer from 0-15.\n", outputArea.getRows() + 1);
                return;
            }
        }

        RequestManager.queueRequest(this);
    }

    @Override
    public void run() {
        toggleRunButton(button);

        double average = IntStream.of(numbers).sum() / 5d;
        // Using the functions in Functions.java!
        int closest = Functions.getClosestToAverage(numbers, average);
        long factorial = Functions.getFactorial(closest);

        updateOutputArea(outputArea,
                outputArea.getText() + "----------------------------------------------------------------\n"
                        + "For the given numbers: "
                        + Arrays.toString(numbers) + ".\n"
                        + "Average: " + average + "\n"
                        + "Closest integer: " + closest + "\n"
                        + "Factorial of closest integer: " + factorial + "\n",
                outputArea.getRows() + 5
        );
        toggleRunButton(button);
    }
}
