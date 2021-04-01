/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.util;

/**
 * This enum is used to distinguish button types,
 * when creating different button action listeners.
 * Using an enum makes it easy to distinguish these buttons by assigning
 * a ButtonType value to them.
 */
public enum ButtonType {

    /**
     * Represents the "Help" button in the UI.
     */
    HELP,

    /**
     * Represents the "Credits" button in the UI.
     */
    CREDITS,

    /**
     * Represents the "Play" button in the UI.
     */
    PLAY,

    /**
     * Represents the "Roll" button in the UI.
     */
    ROLL,

    /**
     * Represents the "Back" button in the UI, after games have finished.
     */
    BACK,

    /**
     * Represents the "Back" button in the UI in help menus.
     */
    ROLL_BACK
}