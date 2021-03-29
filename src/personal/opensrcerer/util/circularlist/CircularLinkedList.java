package personal.opensrcerer.util.circularlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An implementation of a Circular Linked List that is used in the SnakeEyes game to pass turns in a circular fashion.
 * The structure of the List is generified to maintain compatibility but in the project, the Type argument X is a Player object.
 * @param <X> The type of data that this list contains.
 */
public class CircularLinkedList<X> {
    /**
     * The first element on the list.
     */
    private X firstElement = null;

    /**
     * The current node on the list.
     */
    private CircularNode<X> current = null;

    /**
     * The previous node of the list.
     */
    private CircularNode<X> previous = null;

    /**
     * Represents the times that this list has done a full loop.
     */
    private int timesListLooped = 0;

    /**
     * Represents the times that this list has done a full loop.
     */
    private int size = 0;

    /**
     * Construct a new CircularLinkedList with no elements.
     */
    public CircularLinkedList() {
    }

    /**
     * Construct a new CircularLinkedList from an array.
     */
    public CircularLinkedList(X[] elements) {
        Arrays.stream(elements).forEach(this::add);
    }

    /**
     * Advance this LinkedList by one node. To be used externally, because it increments the loop counter.
     * @return The player that has been advanced to.
     */
    public X next() {
        if (current != null) {
            previous = current; // Make the current node the previous node
            current = previous.next; // Advance the current node

            if (current.element.equals(firstElement)) {
                ++timesListLooped; // Increment the times this list has looped
            }

            return current.element;
        }
        return null;
    }

    /**
     * Add an element to this LinkedList.
     * @param data Data to insert.
     */
    public void add(X data) {
        this.insert(data);
        this.advance();
        ++size;
    }

    /**
     * Advance this LinkedList by one node. Used for adding elements.
     */
    private void advance() {
        if (current != null) {
            previous = current; // Make the current node the previous node
            current = previous.next; // Advance the current node
        }
    }

    /**
     * Insert new data into the Linked List as a new node.
     * @param data The new data to insert.
     */
    private void insert(X data) {
        CircularNode<X> newNode = new CircularNode<>(data);
        if (current == null) { // Case if the list is empty
            firstElement = data; // Set the first element as this new node's element
            previous = newNode;
            current = newNode;
            newNode.next = newNode;
        } else {
            // Make the new Node the current node but leave the previous node unchanged
            previous.next = newNode;
            newNode.next = current;
            current = newNode;
        }
    }

    /**
     * @return A list consisting of all the elements in this Linked List.
     */
    public List<X> getAll() {
        ArrayList<X> elements = new ArrayList<>();

        elements.add(current.element); // Add the first element and advance
        advance();
        while (!isAtFirst()) {
            elements.add(current.element);
            advance();
        }

        return elements;
    }

    /**
     * @return The current node's element.
     */
    public X getCurrent(){
        return current.element;
    }

    /**
     * @return The number of times this list has looped.
     */
    public int getTimesListLooped() {
        return timesListLooped;
    }

    /**
     * @return The size of this LinkedList.
     */
    public int size() {
        return size;
    }

    /**
     * @return Whether this List's current node contains the firstly added element.
     */
    public boolean isAtFirst() {
        return current.element.equals(firstElement);
    }
}
