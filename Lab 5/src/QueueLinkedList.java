import java.util.Scanner;

class Node{
    private int val;
    private Node next;
    public Node(){
        next = null;
    }
    public Node(int x){
        val= x;
        next = null;
    }
    public int getVal(){
        return this.val;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node n){
        this.next = n;
    }
    public void setVal(int data) {this.val = data;}
}

interface ILinkedBased { }

interface IQueueLinkedList {
    public void enqueue(Object item);
    public Object dequeue();
    public boolean isEmpty();
    public int size();
}


public class QueueLinkedList implements IQueueLinkedList, ILinkedBased {
    Node head; //Dummy node
    Node tail; //last node
    int size = 0;
    public QueueLinkedList() {
        this.head = new Node();
        this.tail = null;
    }

    @Override
    public void enqueue(Object item) {
        Node ele = (Node)item;
        if (head.getNext() == null) {
            head.setNext(ele);
            tail =ele;
            size++;
            return;
        }
        tail.setNext(ele);
        tail = tail.getNext();
        size++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()) return null;
        Node firstNode = head.getNext();
        head.setNext(firstNode.getNext());
        size--;
        return firstNode;
    }

    @Override
    public boolean isEmpty() {
        return (head.getNext() == null);
    }

    @Override
    public int size() {
        return size;
    }

    public void print(){
        System.out.print("[");
        print(head.getNext());
        System.out.print("]");
    }

    public void print(Object item){
        Node ele = (Node)item;
        if(ele == null)
            return;
        print(ele.getNext());
        System.out.print(ele.getVal());
        if(ele != head.getNext())
            System.out.print(", ");
    }


    public static void main(String[] args) {
        QueueLinkedList Q = new QueueLinkedList();
        //read input elements
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }
        //fill queue with elements
        for(int i = arr.length-1; i>=0 ; i--){
            Node n = new Node(arr[i]);
            Q.enqueue(n);
        }

        sin = sc.nextLine();

        switch (sin){
            case "enqueue":
                int el;
                el = sc.nextInt();
                Node n = new Node(el);
                Q.enqueue(n);
                Q.print();
                break;
            case "dequeue":
                if(Q.dequeue()==null){
                    System.out.println("Error");
                    break;
                }
                Q.print();
                break;
            case "isEmpty":
                System.out.println((Q.isEmpty())?"True":"False");
                break;
            case "size":
                System.out.println(Q.size());
                break;
            default:
                System.out.print("Error");
        }

    }
}
