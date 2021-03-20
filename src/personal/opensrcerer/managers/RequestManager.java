/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.managers;

import personal.opensrcerer.actions.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class manages every request that gets
 * sent to the queue. By only having one spot open and
 * a dedicated thread, it allows one request at a time
 * to have independency from the GUI.
 *
 * This class is actually built for more threads but is currently
 * only using one thread.
 */
public final class RequestManager {

    /**
     * ExecutorService to manage one request at a time.
     */
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Queue accessible by the ExecutorService's single thread that manages requests.
     */
    private static final LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>(1);

    static {
        // Runnable that gets parked to wait for requests.
        Runnable runRequests = () -> {
            while (true) {
                Request request = null;

                try {
                    request = requests.take();
                } catch (InterruptedException ex) {
                    System.out.println("Thread interrupted! Program shutdown expected.");
                }

                if (request == null) {
                    return;
                }

                request.run();
            }
        };

        // submits the request to the executor
        executor.submit(runRequests);
    }

    /**
     * Adds the request to the requests array of active requests.
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