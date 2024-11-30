import java.util.Objects;
import java.util.Scanner;


interface IStack {

    /*** Removes the element at the top of stack and returns that element.
     * @return top of stack element, or through exception if empty
     */

    public Object pop();

    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */

    public Object peek();

    /*** Pushes an item onto the top of this stack.
     * @param object to insert*
     */

    public void push(Object element);

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();

    public int size();
}

class Node {
    Object data;
    Node next;

    Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

public class MyStack implements IStack {
    private int size;
    private Node head;

    public MyStack(){
        this.head = null;
        this.size = 0;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String userInput = getTrim(in);
        MyStack stack = new MyStack();
        try{
            createStack(userInput, stack);
        } catch(Exception e){}
        String op = in.nextLine();
        switch (op){
            case "push":
                Object element = in.nextInt();
                stack.push(element);
                printStack(stack);
                break;
            case "pop":
                stack.pop();
                printStack(stack);
                break;
            case "peek":
                System.out.println(stack.peek());
                break;
            case "isEmpty":
                if (stack.isEmpty()) System.out.println("True");
                else System.out.println("False");
                break;
            case "size":
                System.out.println(stack.size());
                break;
            default:
                System.out.println("Error");
                System.exit(0);
        }
    }

    @Override
    public Object pop() {
        if (this.head == null){
            System.out.println("Error");
            System.exit(0);
        }
        Node returnedNode = this.head;
        this.head = this.head.next;
        this.size--;
        return returnedNode;
    }

    @Override
    public Object peek() {
        if (this.head == null){
            System.out.println("Error");
            System.exit(0);
        }
        return this.head.data;
    }

    @Override
    public void push(Object element) {
        Node newNode = new Node(element);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private static void createStack(String userInput, MyStack stack) throws Exception {
        String[] stringArray = userInput.split(", ");
        for (String s : stringArray) {
            try {
                addLast(stack, Integer.parseInt(s));
            } catch (Exception error) {
                throw new Exception();
            }
        }
    }
    private static void printStack(MyStack stack){
        Node tempHead = stack.head;
        System.out.print("[");
        while (tempHead != null){
            if (tempHead.next != null) System.out.print(tempHead.data + ", ");
            else System.out.print(tempHead.data);
            tempHead = tempHead.next;
        }
        System.out.print("]");
    }
    private static String getTrim(Scanner in) {
        return in.nextLine().replace("[", "").replace("]", "").trim();
    }
    private static void addLast(MyStack stack, Object element){
        if (stack.head != null) {
            Node tempNode = stack.head;
            while (tempNode.next != null) tempNode = tempNode.next;
            tempNode.next = new Node(element);
            tempNode.next.next = null;
        } else{
            stack.head = new Node(element);
            stack.head.next = null;
        }
        stack.size++;
    }
}
