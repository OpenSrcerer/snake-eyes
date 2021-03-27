/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.actions;

import personal.opensrcerer.util.Player;
import personal.opensrcerer.util.RequestDispatcher;

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

    }
}
