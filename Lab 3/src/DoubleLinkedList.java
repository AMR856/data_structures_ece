import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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

class DoublyNode {
    Object data;
    DoublyNode next;
    DoublyNode prev;

    DoublyNode(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DoubleLinkedList implements ILinkedList {
    private DoublyNode head;
    private DoublyNode tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();
        DoublyNode newNode = new DoublyNode(element);
        
        if (index == 0) {
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == this.size) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            DoublyNode current = getNodeAt(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        thi.size++;
    }

    @Override
    public void add(Object element) {
        add(this.size, element);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return getNodeAt(index).data;
    }

    private DoublyNode getNodeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        
        DoublyNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }

    @Override
    public void set(int index, Object element) {
        DoublyNode node = getNodeAt(index);
        node.data = element;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        
        if (index == 0) {
            if (head == tail) head = tail = null;
            else {
                head = head.next;
                head.prev = null;
            }
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            DoublyNode current = getNodeAt(index);
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) throw new IndexOutOfBoundsException();
        
        DoubleLinkedList subList = new DoubleLinkedList();
        DoublyNode current = getNodeAt(fromIndex);
        for (int i = fromIndex; i <= toIndex; i++) {
            subList.add(current.data);
            current = current.next;
        }
        return subList;
    }

    @Override
    public boolean contains(Object o) {
        DoublyNode current = head;
        while (current != null) {
            if (current.data.equals(o)) return true;
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        int element, index, index1;
        DoubleLinkedList list = new DoubleLinkedList();
        Scanner in = new Scanner(System.in);
        String userInput = getTrim(in);
        String requiredCommand = in.nextLine().trim();
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
                DoubleLinkedList newList = (DoubleLinkedList) list.sublist(index, index1);
                printList(newList);
                }
                catch (Exception _){
                    System.out.println("Error");
                }
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    
    private static void printList(DoubleLinkedList list) {
        DoublyNode currentNode = list.head;
        System.out.print("[");
        while (currentNode != null) {
            System.out.print(currentNode.data);
            if (currentNode.next != null) System.out.print(", ");
            currentNode = currentNode.next;
        }
        System.out.println("]");
    }

    private static void createList(String userInput, DoubleLinkedList list) throws Exception {
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
