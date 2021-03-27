/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.actions;

import personal.opensrcerer.util.Player;
import personal.opensrcerer.util.RequestDispatcher;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Add description here
 */
public class RollRequest implements Request {
    /**
     * Player that initiated this request.
     */
    private final Player player;

    /**
     * Create a new RollRequest object and put it in the
     * Request queue.
     */
    public RollRequest(Player player) {
        this.outputArea = outputArea;

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

        RequestDispatcher.queueRequest(this);
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
