package personal.opensrcerer.util.circularList;

/**
 * This class is a node for the Circular Linked List.
 * @param <X> Type of data to contain.
 */
class CircularNode<X> {
    /**
     * The current element in the node.
     */
    protected X element;

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
