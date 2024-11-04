import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ICalculator {
    /**
     * Adds given two numbers
     * @param x first number
     * @param y second number
     * @return the sum of the two numbers
     */
    int add(int x, int y);
    /**
     * Divides two numbers
     * @param x first number
     * @param y second number
     * @return the division result
     */
    float divide(int x, int y) throws RuntimeException;
}


public class Calculator implements ICalculator{
    /* Implement your calculator class here*/
    public int add(int x, int y){
        return x + y;
    }
    public float divide(int x, int y){
        return (float) x / y;
    }
    public static void main(String []args){
        Scanner in = new Scanner(System.in);
        Calculator calc = new Calculator();
        int num1, num2;
        String op;
        String line = in.nextLine();
        num1 = Integer.parseInt(line.split(" ")[0]);
        op = line.split(" ")[1];
        num2 = Integer.parseInt(line.split(" ")[2]);

        if (op.compareTo("+") == 0) System.out.println(calc.add(num1, num2));
        else {
            if (num2 == 0) System.out.println("Error");
            else System.out.println(calc.divide(num1, num2));
        }
    }
}
