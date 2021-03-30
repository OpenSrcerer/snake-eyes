/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.actions;

import personal.opensrcerer.util.Player;
import personal.opensrcerer.util.RequestDispatcher;
import personal.opensrcerer.util.SnakeEyes;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
        this.player = player;
        RequestDispatcher.queueRequest(this);
    }

    @Override
    public void run() {
        SnakeEyes.getRollButton().toggle(); // Lock the button so the user does not click it relentlessly
        SnakeEyes.getBanner().update("Rolling...");
        simulateRoll(); // Simulate dice being rolled
        player.roll(getRandomDice()); // Perform internal player dice and score additions that have to be synchronized
        // Unlock the 5 seconds later (give time to player to read their score)
        RequestDispatcher.schedule(() -> {
            SnakeEyes.getRollButton().toggle();
            SnakeEyes.getBanner().update();
            SnakeEyes.getScoreboard().refresh();
            SnakeEyes.getDiceboard().refreshUnrolled();
        }, 5, TimeUnit.SECONDS);
    }

    /**
     * Simulates the dice rolling by picking random dice values and refreshing the Diceboard.
     */
    private void simulateRoll() {
        for (short rolls = 0; rolls < 19; ++rolls) {
            // Random function I made up to make timer look interesting
            short timer = (short) ((1/3f * Math.pow(rolls, 2)) + 100);
            try {
                Thread.sleep(timer);
            } catch (InterruptedException ex) {
                System.out.println("Something went wrong! " + ex);
            }
            SnakeEyes.getDiceboard().refresh(getRandomDice());
        }
    }

    /**
     * @return A pseudorandom short from 1 - 6 that matches a die's number.
     */
    private static short[] getRandomDice() {
        return new short[] {
                (short) ThreadLocalRandom.current().nextInt(1, 7),
                (short) ThreadLocalRandom.current().nextInt(1, 7)
        };
    }
}
