package personal.opensrcerer.util.circularlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
     * Contains the number of elements in this linked list.
     */
    private int size;

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
     * Add an element to this LinkedList.
     * @param data Data to insert.
     */
    public void add(X data) {
        this.insert(data);
        this.next();
        ++size;
    }

    /**
     * Advance this LinkedList by one node.
     */
    public void next() {
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

        // Loop over the whole list and retrieve every element
        for (int index = 0; index < size; ++index) {
            elements.add(current.element);
            next();
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
     * @return The size of this LinkedList.
     */
    public int size() {
        return size;
    }

    /**
     * @return The number of elements in this list that satisfy the given Predicate.
     */
    public int getElementsThat(Predicate<X> predicate) {
        return (int) getAll().stream().filter(predicate).count();
    }

    /**
     * This method sets this list's current element to the closest next element on the list
     * that satisfy the given Predicate. Returns true upon successfully finding a match.
     * If the list is looped fully and an element is not found, this method returns false.
     * @return True if successfully set, false otherwise.
     */
    public boolean advanceTo(Predicate<X> predicate) {
        for (int index = 0; index < size; ++index) {
            next(); // Advance to the next element
            if (predicate.test(getCurrent())) {
                return true; // Test if the element matches, return true if found.
            }
        }
        return false;
    }

    /**
     * Set the list's current node to the first node.
     */
    public void setToFirst() {
        while (!current.element.equals(firstElement)) {
            next();
        }
    }
}
