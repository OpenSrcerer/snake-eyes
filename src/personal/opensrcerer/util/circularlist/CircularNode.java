package personal.opensrcerer.util.circularlist;

/**
 * This class is a node for the Circular Linked List.
 * @param <X> Type of data to contain.
 */
public class CircularNode<X> {
    /**
     * The current element in the node.
     */
    protected X element;

    /**
     * The next element in the node.
     */
    protected CircularNode<X> next;

    protected CircularNode() {

    }
}
