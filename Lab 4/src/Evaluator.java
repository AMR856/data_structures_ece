import java.util.*;

interface IStackEvaluator {

    /*** Removes the element at the top of stack and returns that element.
     * @return top of stack element, or through exception if empty
     */

    public Object pop() throws Exception;

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


interface IExpressionEvaluator {

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression infix expression
     * @return postfix expression
     */

    public String infixToPostfix(String expression);


    /**
     * Evaluate a postfix numeric expression, with a single space separator
     * @param expression postfix expression
     * @return the expression evaluated value
     */

    public int evaluate(String expression);

}
class MyStackEvaluator implements IStackEvaluator {
    public int size;
    public NodeEvaluator head;

    public MyStackEvaluator(){
        this.head = null;
        this.size = 0;
    }
    @Override
    public Object pop() throws Exception{
        if (this.head == null){
            throw new Exception();
        }
        NodeEvaluator returnedNode = this.head;
        this.head = this.head.next;
        this.size--;
        return returnedNode.data;
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
        NodeEvaluator newNode = new NodeEvaluator(element);
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
}

class NodeEvaluator {
    Object data;
    NodeEvaluator next;

    NodeEvaluator(Object data) {
        this.data = data;
        this.next = null;
    }
}

public class Evaluator implements IExpressionEvaluator {
    private MyStackEvaluator stack;
    private int a;
    private int b;
    private int c;
    Evaluator(){
        this.stack = new MyStackEvaluator();
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }
    public void setA(int a) {
        this.a = a;
    }
    public void setB(int b) {
        this.b = b;
    }
    public void setC(int c) {
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().replace("^--", "^").replace("*--", "*").replace("/--", "/").replace("+--", "+").replace("(--", "(").replace("(---", "-");
        userInput = doubleNegativeChecker(userInput);
        Evaluator evaluator = new Evaluator();
        int a = getVariable(in.nextLine());
        int b = getVariable(in.nextLine());
        int c = getVariable(in.nextLine());
        evaluator.setA(a);
        evaluator.setB(b);
        evaluator.setC(c);
        try {
            String postfixExpression = evaluator.infixToPostfix(userInput);
            if (evaluator.isOperator(userInput.charAt(userInput.length() - 1))
                    || userInput.charAt(userInput.length() - 1) == '('){
                throw new Exception();
            }
            if (postfixExpression.contains("(") || postfixExpression.contains(")")){
                throw new Exception();
            }
            int result = evaluator.evaluate(postfixExpression);
            System.out.println(postfixExpression);
            System.out.println(result);
        } catch(Exception e){
            System.out.println("Error");
            System.exit(0);
        }
    }
    public static String doubleNegativeChecker(String input){
        if (input.startsWith("--")) input = input.replaceFirst("--", "");
        if (input.contains("--")) input = input.replaceAll("--", "+");
        return input;
    }
    public boolean isOperator(char c){
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }
    public boolean isOperand(char c){
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'c')) return true;
        else if (c >= 'd' && c <= 'z'){
            System.out.println("Error");
            System.exit(0);
        }
        return false;
    }
    public static boolean isPostfixOperand(char c){
        return (c == 'a' || c == 'b' || c == 'c');
    }
    public static int opWeight(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else if (c == '^') return 3; // Added power operator
        else return 0; // Handle invalid operators
    }
    public int getCorrespondingValue(char c){
        if (c == 'a') return getA();
        else if(c == 'b') return getB();
        else if(c == 'c') return getC();
        else return 0;
    }
    public static boolean hasHigherPrecedence(char top, char expressionOp) {
        int op1 = opWeight(top);
        int op2 = opWeight(expressionOp);
        if (op1 == op2 && top == '^') return false;
        return op1 >= op2;
    }
    @Override
    public String infixToPostfix(String expression) {
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < expression.length(); i++){
            if (isOperand(expression.charAt(i))) {
                postfix.append(expression.charAt(i));
            }
            else if(isOperator(expression.charAt(i))){
                while (!stack.isEmpty() &&
                        (char) stack.peek() != '(' &&
                        hasHigherPrecedence((char) stack.peek(), expression.charAt(i))){
                    postfix.append(stack.peek());
                    try{
                        stack.pop();
                    } catch (Exception e) {
                        System.out.println("Error");
                        System.exit(0);
                    }
                }
                stack.push(expression.charAt(i));
            }
            else if (expression.charAt(i) == '(') stack.push(expression.charAt(i));
            else if (expression.charAt(i) == ')'){
                while (!stack.isEmpty() && (char) stack.peek() != '('){
                    postfix.append(stack.peek());
                    try {
                        stack.pop();
                    } catch (Exception e) {
                        System.out.println("Error");
                        System.exit(0);
                    }
                }
                try {
                    stack.pop();
                } catch (Exception e) {
                    System.out.println("Error");
                    System.exit(0);
                }
            }
        }
        while (!stack.isEmpty()){
            postfix.append(stack.peek());
            try {
                stack.pop();
            } catch (Exception e) {
                System.out.println("Error");
                System.exit(0);
            }
        }
        return postfix.toString();
    }
    public int calculateResult(int op1, int op2, char op){
        if (op == '+') return op1 + op2;
        else if (op == '-') return op1 - op2;
        else if (op == '*') return op1 * op2;
        else if(op == '/') return op1 / op2;
        else {
            if (op2 < 0) return 0;
            else return (int) Math.pow(op1, op2);
        }
    }
    @Override
    public int evaluate(String expression) {
        for (int i = 0; i < expression.length(); i++){
            if (isPostfixOperand(expression.charAt(i))) stack.push(getCorrespondingValue(expression.charAt(i)));
            else if (isOperator(expression.charAt(i))) {
                int op1 = 0;
                int op2 = 0;
                try {
                    op2 = (int) stack.pop();
                    op1 = (int) stack.pop();
                } catch (Exception e) {
                    if (expression.charAt(i) != '-') {
                        System.out.println("Error");
                        System.exit(0);
                    }
                    op1 = 0;
                }
                int res = calculateResult(op1, op2, expression.charAt(i));
                stack.push(res);
            }
        }
        try {
            return (int) stack.pop();
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(0);
            return 0;
        }
    }
    public static int getVariable(String line){
        try{
            line = line.replaceAll("[a-c]=", "");
            return (Integer.parseInt(line));
        } catch(Exception e){
            System.out.println("Error");
            System.exit(0);
            return 0;
        }
    }
}
