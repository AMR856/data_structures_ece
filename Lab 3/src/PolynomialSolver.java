import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedListPoly {
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
     * @return the number of elements in this list.
     */
    public int size();

}

class ListNode {
    Object data;
    ListNode next;

    ListNode(Object data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList implements ILinkedListPoly{

    /* Implement your linked list class here*/
    private ListNode head;
    private int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }
    @Override
    public void add(int index, Object element){
        if (this.head == null && index == 0){
            this.head = new ListNode(element);
            this.size++;
            return;
        } else if (index == 0){
            ListNode newNode = new ListNode(element);
            newNode.next = this.head;
            this.head = newNode;
            return;
        }

        ListNode newNode = new ListNode(element);
        ListNode currentNode = this.head;
        int currentIndex = 0;
        if (index <= -1 || index > size){
            throw new IndexOutOfBoundsException();
        }
        while (currentIndex < index - 1){
            assert currentNode != null;
            currentNode = currentNode.next;
            currentIndex++;
        }
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
            throw new IndexOutOfBoundsException();
        }
        ListNode currentNode = this.head;
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
            throw new IndexOutOfBoundsException();
        }
        ListNode currentNode = this.head;
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
    public int size() {
        return this.size;
    }
}

interface IPolynomialSolver {
    /**
     * Set polynomial terms (coefficients & exponents)
     * @param poly: name of the polynomial
     * @param terms: array of [coefficients][exponents]
     */
    void setPolynomial(char poly, int[][] terms);

    /**
     * Print the polynomial in ordered human readable representation
     * @param poly: name of the polynomial
     * @return: polynomial in the form like 27x^2+x-1
     */
    String print(char poly);

    /**
     * Clear the polynomial
     * @param poly: name of the polynomial
     */
    void clearPolynomial(char poly);


    /**
     * Evaluate the polynomial
     * @param poly: name of the polynomial
     * @param value: the polynomial constant value
     * @return the value of the polynomial
     */
    float evaluatePolynomial(char poly, float value);

    /**
     * Add two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial
     */
    int[][] add(char poly1, char poly2);

    /**
     * Subtract two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);

    /**
     * Multiply two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return: the result polynomial
     */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver{
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        LinkedList A = new LinkedList();
        LinkedList B = new LinkedList();
        LinkedList C = new LinkedList();
        LinkedList R = new LinkedList();
        
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        
    }

    @Override
    public String print(char poly) {
        return "";
    }

    @Override
    public void clearPolynomial(char poly) {

    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        return 0;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        return new int[0][];
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        return new int[0][];
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
    }

    private static void createList(String userInput, SingleLinkedList list) throws Exception {
        String []stringArray = userInput.split(",");
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
