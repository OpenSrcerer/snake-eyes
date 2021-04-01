/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.util;

/**
 * Used to better identify players and their current statuses.
 */
public enum PlayerStatus {
    /**
     * Shows that a player has not rolled yet for this round.
     */
    AWAITING_POINT_ROLL,

    /**
     * Shows that a player has rolled, did not win or lose, and is currently continuously rolling.
     */
    PLAYING,

    /**
     * Shows that a player has either won/lost, and as such is no longer rolling in the current round.
     */
    FINISHED_ROUND
}
