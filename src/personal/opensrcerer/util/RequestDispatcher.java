/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.util;

import personal.opensrcerer.actions.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class executes every request that gets
 * sent to the queue. By using an ExecutorService it allows for independence from the GUI thread.
 *
 * RequestDispatcher is actually built for more threads but is currently only using one thread.
 */
public final class RequestDispatcher {

    /**
     * ExecutorService to manage one request at a time.
     */
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Queue accessible by the ExecutorService's single thread that manages requests.
     */
    private static final LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>(1);

    static {
        // Spinning code that awaits requests to be put into the queue.
        Runnable runRequests = () -> {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("Bye bye!");
                    return;
                }

                try {
                    Request request = requests.take();
                    request.run();
                } catch (Exception | Error e) {
                    System.out.println("Something went wrong! " + e.getMessage());
                }
            }
        };

        // submits the request to the executor
        executor.submit(runRequests);
    }

    /**
     * Adds the request to the requests array of active requests.
     * Dumps the request if it is beyond the queue' capacity.
     * @param request Request to be added.
     */
    public static void queueRequest(Request request) {
        requests.offer(request);
    }

    /**
     * Shuts the executors down and interrupts threads.
     */
    public static void killExecutor() {
        executor.shutdownNow();
    }
}