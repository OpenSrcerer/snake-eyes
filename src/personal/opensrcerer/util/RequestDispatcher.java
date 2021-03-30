/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.util;

import personal.opensrcerer.actions.Request;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class executes every request that gets
 * sent to the queue. By using an ExecutorService it allows for independence from the GUI thread.
 *
 * RequestDispatcher is actually built for more threads but is currently only using one thread.
 */
public final class RequestDispatcher {

    /**
     * ExecutorService with a thread to manage one request at a time, and one for scheduling.
     */
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

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
     * Schedule a Runnable on this ScheduledExecutorService.
     * @param r Runnable to schedule.
     * @param delay Value of the delay.
     * @param unit The TimeUnit of the delay.
     */
    public static void schedule(Runnable r, long delay, TimeUnit unit) {
        executor.schedule(r, delay, unit);
    }

    /**
     * Shuts the executors down and interrupts threads.
     */
    public static void killExecutor() {
        executor.shutdownNow();
    }
}