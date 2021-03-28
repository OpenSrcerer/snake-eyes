package personal.opensrcerer.util.circularlist;

/**
 * An implementation of a Circular Linked List that is used in the SnakeEyes game to pass turns in a circular fashion.
 * @param <X> The type of data that this list contains.
 */
public class CircularLinkedList<X> {

    /**
     * The current node on the list.
     */
    private CircularNode<X> current;

    /**
     * The previous node of the list.
     */
    private CircularNode<X> previous;

    /**
     * @return The current node on the list.
     */
    public CircularNode<X> getCurrent(){
        return current;
    }


    public void advance() {
        if (current != null) {
            previous = current; // Make the current node the previous node
            current = previous.next; // Make the 
        }
    }

    public void add(int data) {
        this.insert(data);
        this.advance();
    }

    public void insert(int data) {
        CircularNode<X> newLink = new CircularNode<>(data);
        if (current == null) {
            current = newLink;
            previous = newLink;
            newLink.next = newLink; // tail.next = current!
        } else {
            previous.next = newLink; // tail.next = current!
            newLink.next = current;
            current = newLink; // tail is unchanged, newLink is current
        }
    }
}
