import java.util.*;

interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item);
    /*** Removes the object at the queue rear and returnsit.*/
    public Object dequeue();
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}


public class ArrayQueue implements IQueue {
    private final int capacity;
    private int size;
    private int front, rear;
    private final Object[] queueArray;

    public ArrayQueue() {
        this.capacity = 1000;
        this.size = 0;
        queueArray = new Object[this.capacity];
        this.front = 0;
        this.rear = -1;
    }

    public void enqueue(Object item) {
        if (this.size == this.capacity) {
            throw new IllegalStateException("Queue is full");
        }
        this.rear = (this.rear + 1) % this.capacity;
        this.queueArray[this.rear] = item;
        this.size++;
    }

    public Object dequeue() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        Object returnedObject = this.queueArray[this.front];
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return returnedObject;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput = getTrim(in);
        ArrayQueue queue = new ArrayQueue();
        String op;
        op = in.nextLine().trim();

        try {
            createQueue(userInput, queue);
        } catch (Exception e) {
            System.out.println("Error");
            return;
        }

        try {
            switch (op) {
                case "enqueue":
                    if (!in.hasNextInt()) {
                        throw new InputMismatchException("Invalid input");
                    }
                    int n = in.nextInt();
                    queue.enqueue(n);
                    printQueue(queue);
                    break;
                case "dequeue":
                    queue.dequeue();
                    printQueue(queue);
                    break;
                case "size":
                    System.out.print(queue.size());
                    break;
                case "isEmpty":
                    System.out.print(queue.isEmpty() ? "True" : "False");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void createQueue(String userInput, ArrayQueue queue) throws Exception {
        String[] stringArray = userInput.split(", ");
        List<Object> list = new ArrayList<>(Arrays.asList(stringArray));
        Collections.reverse(list);
        for (Object s : list) {
            if (!((String) s).isEmpty()) {
                try {
                    queue.enqueue(Integer.parseInt((String) s));
                } catch (NumberFormatException e) {
                    throw new Exception("Invalid number format");
                }
            }
        }
    }

    private static void printQueue(ArrayQueue queue) {
        int length = queue.size();
        List<Object> printingList = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            printingList.add(queue.dequeue());
        }
        Collections.reverse(printingList);
        System.out.print(printingList);
    }

    private static String getTrim(Scanner in) {
        String userInput = in.nextLine();
        if (!userInput.startsWith("[") || !userInput.endsWith("]")) {
            throw new IllegalArgumentException("Input should be enclosed in brackets");
        }
        return userInput.substring(1, userInput.length() - 1).trim();
    }
}
