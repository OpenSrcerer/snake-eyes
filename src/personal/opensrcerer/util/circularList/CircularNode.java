/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.util.circularList;

/**
 * This class is a node for the Circular Linked List.
 * @param <X> Type of data to contain.
 */
class CircularNode<X> {
    /**
     * The current element in the node.
     */
    protected final X element;

    /**
     * The next element in the node.
     */
    protected CircularNode<X> next;

    /**
     * Create a new CircularNode with the given data.
     * @param data Data to store.
     */
    protected CircularNode(X data) {
        this.element = data;
    }
}
