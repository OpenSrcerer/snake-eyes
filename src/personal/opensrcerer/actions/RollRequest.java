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
        simulateRoll(); // Simulate dice being rolled
        player.roll(getRandomDice());
        SnakeEyes.getRollButton().toggle(); // Unlock the button because rolling is over
    }

    /**
     * Simulates the dice rolling by picking random dice values and refreshing the Diceboard.
     */
    private void simulateRoll() {
        for (short rolls = 0; rolls < 20; ++rolls) {
            // Random function I made up to make timer look interesting
            short timer = (short) ((1/3f * Math.pow(rolls, 2)) + 100);
            try {
                Thread.sleep(timer);
            } catch (InterruptedException ex) {
                System.out.println("Something went wrong! " + ex);
            }
            SnakeEyes.getDiceboard().refresh(player, getRandomDice());
        }
    }

    /**
     * @return A pseudorandom short from 0 - 5 that matches a die's number - 1.
     * This is the case because the die images are stored in an array and the indexes for 1 - 6 are 0 - 5.
     */
    private static short[] getRandomDice() {
        return new short[] {
                (short) ThreadLocalRandom.current().nextInt(0, 6),
                (short) ThreadLocalRandom.current().nextInt(0, 6)
        };
    }
}
