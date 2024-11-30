import java.util.*;
import java.lang.Math;

interface ILinkedListPoly {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Term element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Term element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Term get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Term element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
}

class ListNode {
    Term data;
    ListNode next;

    ListNode(Term data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList implements ILinkedListPoly{

    /* Implement your linked list class here*/
    ListNode head;
    int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }
    @Override
    public void add(int index, Term element){
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
    public void add(Term element) {
        add(this.size, element);
    }
    @Override
    public Term get(int index) {
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
    public void set(int index, Term element) {
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

class Term {
    int exponent;
    int coefficient;

    Term (int exponent, int coefficient){
        this.exponent = exponent;
        this.coefficient = coefficient;
    }
}

public class PolynomialSolver implements IPolynomialSolver {

    private LinkedList A;
    private LinkedList B;
    private LinkedList C;
    private LinkedList R;

    PolynomialSolver(){
        this.A = new LinkedList();
        this.B = new LinkedList();
        this.C = new LinkedList();
        this.R = new LinkedList();
    }

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        PolynomialSolver poly = new PolynomialSolver();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            sc.nextLine();
            char polyName1, polyName2;
            float evalFloatIn;
            int [][]usedArray;
            switch (command) {
                case "set":
                    polyName1 = sc.next().charAt(0);
                    sc.nextLine();
                    String userInput = getTrim(sc);
                    try {
                        poly.setPolynomial(polyName1, poly.createList(userInput));
                    } catch (Exception _) {
                        System.out.println("Error");
                        System.exit(0);
                    }
                    break;
                case "clear":
                    polyName1 = sc.next().toUpperCase().charAt(0);
                    poly.clearPolynomial(polyName1);
                    break;
                case "print":
                    polyName1 = sc.next().toUpperCase().charAt(0);
                    System.out.println(poly.print(polyName1));
                    break;
                case "add":
                    polyName1 = sc.next().toUpperCase().charAt(0);
                    sc.nextLine();
                    polyName2 = sc.next().toUpperCase().charAt(0);
                    poly.add(polyName1, polyName2);
                    System.out.println(poly.print('R'));
                    break;
                case "sub":
                    polyName1 = sc.next().toUpperCase().charAt(0);
                    sc.nextLine();
                    polyName2 = sc.next().toUpperCase().charAt(0);
                    poly.subtract(polyName1, polyName2);
                    System.out.println(poly.print('R'));
                    break;
                case "mult":
                    polyName1 = sc.next().toUpperCase().charAt(0);
                    sc.nextLine();
                    polyName2 = sc.next().toUpperCase().charAt(0);
                    poly.multiply(polyName1, polyName2);
                    System.out.println(poly.print('R'));
                    break;
                case "eval":
                    polyName1 = sc.next().toUpperCase().charAt(0);
                    sc.nextLine();
                    evalFloatIn = sc.nextFloat();
                    System.out.println((int) poly.evaluatePolynomial(polyName1, evalFloatIn));
                    break;
                default:
                    System.out.println("Error");
                    System.exit(0);
            }
        }
    }
    @Override
    public void setPolynomial(char poly, int[][] terms) {
        LinkedList MyPoly = null;
        switch (poly) {
            case 'A':
                MyPoly = A;
                break;
            case 'B':
                MyPoly = B;
                break;
            case 'C':
                MyPoly = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }

        try {
            for (int[] term : terms) {
                Term t = new Term(term[0], term[1]);
                MyPoly.add(t);
            }
        } catch (Exception e) {
            System.out.print("Error");
            System.exit(0);
        }
    }
    @Override
    public String print(char poly) {
        LinkedList MyPoly = null;
        switch (poly) {
            case 'A':
                MyPoly = A;
                break;
            case 'B':
                MyPoly = B;
                break;
            case 'C':
                MyPoly = C;
                break;
            case 'R':
                MyPoly = R;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }

        StringBuilder out = new StringBuilder();
        ListNode p = MyPoly.head;
        if (p == null) {
            System.out.print("Error");
            System.exit(0);
        }

        try {
            boolean FirstTerm = true;
            while (p != null) {
                Term temp = p.data;
                if (temp.coefficient > 0 && !FirstTerm) {
                    out.append("+");
                }

                if (temp.coefficient < 0) {
                    out.append("-");
                }
                if (Math.abs(temp.coefficient) > 1) {
                    out.append(Math.abs(temp.coefficient));
                }
                if (temp.exponent >= 2 && temp.coefficient != 0) {
                    out.append("x^").append(temp.exponent);
                } else if (temp.exponent == 1 && temp.coefficient != 0) {
                    out.append("x");
                }
                FirstTerm = false;
                p = p.next;
            }
        } catch (Exception e) {
            System.out.print("Error");
        }
        return out.toString();
    }

    @Override
    public void clearPolynomial(char poly) {
        LinkedList myPoly = null;
        switch (poly) {
            case 'A':
                myPoly = A;
                break;
            case 'B':
                myPoly = B;
                break;
            case 'C':
                myPoly = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }
        try {
            myPoly.clear();
            if (myPoly.head == null) System.out.println("[]");
        } catch (Exception e) {
            System.out.print("Error");
            System.exit(0);
        }
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        LinkedList list = null;
        switch (poly) {
            case 'A':
                list = this.A;
                break;
            case 'B':
                list = this.B;
                break;
            case 'C':
                list = this.C;
                break;
            default:
                System.out.println("Error");
                System.exit(0);
        }
        float returnedResult = 0;
        if (list == null || list.head == null){
            System.out.println("Error");
            System.exit(0);
        }
        ListNode current = list.head;
        while (current != null){
            returnedResult += (Math.pow(value, current.data.exponent) * current.data.coefficient);
            current = current.next;
        }
        return returnedResult;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        LinkedList list1 = null;
        LinkedList list2 = null;

        switch (poly1) {
            case 'A':
                list1 = this.A;
                break;
            case 'B':
                list1 = this.B;
                break;
            case 'C':
                list1 = this.C;
                break;
            default:
                System.out.println("Error");
                System.exit(0);
                break;
        }
        switch (poly2) {
            case 'A':
                list2 = this.A;
                break;
            case 'B':
                list2 = this.B;
                break;
            case 'C':
                list2 = this.C;
                break;
            default:
                System.out.println("Error");
                System.exit(0);
                break;
        }
        if (list1 == null || list2 == null){
            System.out.println("Error");
            System.exit(0);
        }
        ListNode current1 = list1.head;
        ListNode current2 = list2.head;
        int list1Length = list1.size;
        int list2Length = list2.size;
        int sum;
        if (list1Length > list2Length){
            R.add(new Term(list1Length - 1, current1.data.coefficient));
            current1 = current1.next;
            list1Length--;
        }
        else if (list2Length > list1Length) {
            R.add(new Term(list2Length - 1, current2.data.coefficient));
            current2 = current2.next;
            list2Length--;
        }
        while (list1Length != 0) {
            sum = current1.data.coefficient + current2.data.coefficient;
            if (sum != 0) R.add(new Term(list1Length - 1, sum));
            current1 = current1.next;
            current2 = current2.next;
            list1Length--;
            list2Length--;
        }
        ListNode currentR = R.head;
        int [][]returnedArray = new int[R.size][2];
        int i = 0;
        while (currentR != null){
            returnedArray[i][0] = currentR.data.exponent;
            returnedArray[i][1] = currentR.data.coefficient;
            i++;
            currentR = currentR.next;
        }
        return returnedArray;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        LinkedList list1 = null;
        LinkedList list2 = null;

        switch (poly1) {
            case 'A':
                list1 = this.A;
                break;
            case 'B':
                list1 = this.B;
                break;
            case 'C':
                list1 = this.C;
                break;
            default:
                System.out.println("Error");
                System.exit(0);
                break;
        }
        switch (poly2) {
            case 'A':
                list2 = this.A;
                break;
            case 'B':
                list2 = this.B;
                break;
            case 'C':
                list2 = this.C;
                break;
            default:
                System.out.println("Error");
                System.exit(0);
                break;
        }
        if (list1 == null || list2 == null){
            System.out.println("Error");
            System.exit(0);
        }
        ListNode current1 = list1.head;
        ListNode current2 = list2.head;
        int list1Length = list1.size;
        int list2Length = list2.size;
        int sum;
        if (list1Length > list2Length){
            R.add(new Term(list1Length - 1, current1.data.coefficient));
            current1 = current1.next;
            list1Length--;
        }
        else if (list2Length > list1Length) {
            R.add(new Term(list2Length - 1, -1 * current2.data.coefficient));
            current2 = current2.next;
            list2Length--;
        }
        while (list1Length != 0) {
            sum = current1.data.coefficient - current2.data.coefficient;
            if (sum != 0) R.add(new Term(list1Length - 1, sum));
            current1 = current1.next;
            current2 = current2.next;
            list1Length--;
            list2Length--;
        }
        ListNode currentR = R.head;
        int [][]returnedArray = new int[R.size][2];
        int i = 0;
        while (currentR != null){
            returnedArray[i][0] = currentR.data.exponent;
            returnedArray[i][1] = currentR.data.coefficient;
            i++;
            currentR = currentR.next;
        }
        return returnedArray;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        LinkedList list1 = null;
        LinkedList list2 = null;
        switch (poly1) {
            case 'A':
                list1 = this.A;
                break;
            case 'B':
                list1 = this.B;
                break;
            case 'C':
                list1 = this.C;
                break;
            case 'R':

            default:
                System.out.println("Error");
                System.exit(0);
        }
        switch (poly2) {
            case 'A':
                list2 = this.A;
                break;
            case 'B':
                list2 = this.B;
                break;
            case 'C':
                list2 = this.C;
                break;
            default:
                System.out.println("Error");
                System.exit(0);
        }
        if (list1 == null || list2 == null){
            System.out.println("Error");
            System.exit(0);
        }

        int n = list1.size;
        int m = list2.size;
        int resultSize = n + m - 1;

        for (int i = 0; i < resultSize; i++) {
            R.add(new Term(resultSize - i - 1, 0)); // Initializing coefficients to zero
        }

        for (int i = 0; i < n; i++) {
            Term term1 = (Term) list1.get(i);

            for (int j = 0; j < m; j++) {
                Term term2 = (Term) list2.get(j);

                int newExponent = term1.exponent + term2.exponent;
                int productCoefficient = term1.coefficient * term2.coefficient;

                int pos = resultSize - newExponent - 1;

                Term resultTerm = (Term) R.get(pos);
                resultTerm.coefficient += productCoefficient; // Accumulate the result
            }
        }


        ListNode currentR = R.head;
        int[][] returnedArray = new int[R.size][2];
        int i = 0;
        while (currentR != null){
            returnedArray[i][0] = currentR.data.exponent;
            returnedArray[i][1] = currentR.data.coefficient;
            i++;
            currentR = currentR.next;
        }
        return returnedArray;
    }

    private int[][] createList(String userInput) throws Exception {
        String[] stringArray = userInput.split(",");
        int[][] returned2D = new int[stringArray.length][2];
        for (int i = 0; i < stringArray.length; i++) {
            try {
                returned2D[i][0] = stringArray.length - i - 1;
                returned2D[i][1] = Integer.parseInt(stringArray[i].trim());
            } catch (Exception e) {
                System.out.println("Error");
                System.exit(0);
            }
        }
        return returned2D;
    }

    private static void printArray(int[][] array){
        for (int[] i: array) {
            for (int j : i) System.out.print(j + ", ");
            System.out.println();
        }
        System.out.println();
    }
    private static String getTrim(Scanner in) {
        return in.nextLine().replace("[", "").replace("]", "").trim();
    }
}