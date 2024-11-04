import java.util.Scanner;

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
            currentNode = currentNode.next;
            currentIndex++;
        }
//        [40, 98, 36, 67, 83, 25, 64, 36, 10, 31, 70]
//        [40, 98, 36, 67, 83, 25, 64, 36, 10, 31, 70]
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
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Error");
        }
        Node currentNode = this.head;
        int currentIndex = 0;
        while (currentIndex != index){
            currentNode = currentNode.next;
            currentIndex++;
        }
        System.out.println(element);
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
        SingleLinkedList newList = new SingleLinkedList();
        for (int i = fromIndex; i <= toIndex; i++){
            newList.add(this.get(i));
        }
        return newList;
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

    public static void main(String[] args) throws Exception {
        int element, index, index1;
        SingleLinkedList list = new SingleLinkedList();
        Scanner in = new Scanner(System.in);
        String userInput = getTrim(in);
        String requiredCommand = in.nextLine();
        if (!userInput.isEmpty()){
            try{
                createList(userInput, list);
            }
            catch (Exception _){
                System.out.println("Error");
                return;
            }
        }
        switch (requiredCommand){
            case "add":
                element = in.nextInt();
                list.add(element);
                printList(list);
                break;
            case "addToIndex":

                index = in.nextInt();
                element = in.nextInt();
                try {
                    list.add(index, element);
                    printList(list);
                } catch (IndexOutOfBoundsException _){
                    System.out.println("Error");
                }
                break;
            case "get":
                index = in.nextInt();
                try{
                    element = (int) list.get(index);
                    System.out.println(element);
                } catch (IndexOutOfBoundsException _){
                    System.out.println("Error");
                }
                break;
            case "set":
                index = in.nextInt();
                element = in.nextInt();
                try{
                    list.set(index, element);
                    printList(list);
                } catch(IndexOutOfBoundsException _){
                    System.out.println("Error");
                }
                break;
            case "clear":
                list.clear();
                System.out.println("[]");
                break;
            case "isEmpty":
                if (list.isEmpty()) System.out.println("True");
                else System.out.println("False");
                break;
            case "size":
                System.out.println(list.size());
                break;
            case "remove":
                index = in.nextInt();
                try{
                    list.remove(index);
                    printList(list);
                } catch(IndexOutOfBoundsException _){
                    System.out.println("Error");
                }
                break;
            case "contains":
                element = in.nextInt();
                if (list.contains(element)) System.out.println("True");
                else System.out.println("False");
                break;
            case "sublist":
                index = in.nextInt();
                index1 = in.nextInt();
                try {
                SingleLinkedList newList = (SingleLinkedList) list.sublist(index, index1);
                printList(newList);
                }
                catch (IndexOutOfBoundsException _){
                    System.out.println("Error");
                }
                break;
        }
    }
    private static void printList(SingleLinkedList list){
        Node currentNode = list.head;
        System.out.print("[");
        while (currentNode.next != null){
            System.out.print(currentNode.data + ", ");
            currentNode = currentNode.next;
        }
        if (currentNode.data != null) System.out.println(currentNode.data + "]");
        else System.out.println("]");
    }
    private static void createList(String userInput, SingleLinkedList list) throws Exception {
        String []stringArray = userInput.split(", ");
        for (String s : stringArray) {
            try {
                list.add(Integer.parseInt(s));
            } catch (Exception error) {
                throw new Exception();
            }
        }
    }
    private static String getTrim(Scanner in) {
        return in.nextLine().replace("[", "").replace("]", "").trim();
    }
}
