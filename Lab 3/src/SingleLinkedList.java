interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();

    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */

    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}

class Node {
    Object data;
    Node next;

    Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

public class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/
    private Node head;
    private int size;

    public SingleLinkedList(){
        this.head = null;
        this.size = 0;
    }

    public void add(int index, Object element){
        if (this.head == null && index == 0){
            this.head = new Node(element);
            System.out.println("Got here");
            this.size++;
            return;
        }
        Node newNode = new Node(element);
        Node currentNode = this.head;
        int currentIndex = 0;
        if (index < -1 || index > size){
            throw new IndexOutOfBoundsException("Error");
        }
        while (currentIndex < index - 1){
            System.out.println("Got here 1");
            currentNode = currentNode.next;
            currentIndex++;
        }
        // A 1
        // B 2
        //
        newNode.next = currentNode.next;
        currentNode.next = newNode;
        this.size++;
    }

    @Override
    public void add(Object element) {
        add(this.size, element);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Error");
        }
        Node currentNode = this.head;
        int currentIndex = 0;
        while (currentIndex != index){
            currentNode = currentNode.next;
            currentIndex++;
        }
        return currentNode.data;
    }

    @Override
    public void set(int index, Object element) {
        if (index < 0 || index > size -1){
            throw new IndexOutOfBoundsException("Error");
        }
        Node currentNode = this.head;
        int currentIndex = 0;
        while (currentIndex != index){
            currentNode = currentNode.next;
            currentIndex++;
        }
        currentNode.data = element;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null || this.size == 0;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > size -1){
            throw new IndexOutOfBoundsException("Error");
        }
        Node currentNode = this.head;
        int currentIndex = 0;
        while (currentIndex != index - 1){
            currentNode = currentNode.next;
            currentIndex++;
        }
        currentNode.next = currentNode.next.next;
        this.size--;
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        Node current = this.head;
        boolean doesContain = false;
        while (current != null){
            if (current.data.equals(o)) {
                doesContain = true;
                break;
            }
            current = current.next;
        }
        return doesContain;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.add("A");
        list.add("B");
        list.add(1, "C"); // Insert C at index 1
        System.out.println("Element at index 1: " + list.get(1)); // Should print C
        System.out.println("List size: " + list.size()); // Should print 3
        System.out.println("List contains B: " + list.contains("B")); // Should print true
        list.remove(1); // Remove element at index 1 (C)
        System.out.println("List contains C: " + list.contains("C")); // Should print false
        list.clear(); // Clear the list
        System.out.println("Is list empty: " + list.isEmpty()); // Should print true
    }
}
