package personal.opensrcerer.util;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 */
public final class SnakeEyes {

    /**
     * An array that contains all the players in the game.
     */
    private final JPlayer[] players;

    public SnakeEyes(JPlayer[] players) {
        this.players = players;
    }

    /**
     * @return All the players in the game as a Stream.
     */
    public Stream<JPlayer> getPlayers() {
        return Arrays.stream(players);
    }
}
