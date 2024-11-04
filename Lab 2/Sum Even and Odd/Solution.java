import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public int[] sumEvenOdd(int[] array) {
        int sumEven = 0, sumOdd = 0;
        for (int num: array){
            if (num % 2 == 0) sumEven += num;
            else sumOdd += num;
        }
        return new int[]{sumEven, sumOdd};
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String userInput = getTrim(in);
        int []intArray = getIntArray(userInput);
        Solution solutionObject = new Solution();
        int []solutionArray = solutionObject.sumEvenOdd(intArray);
        System.out.println("[" + solutionArray[0] + ", " + solutionArray[1] + "]");
    }
    private static int[] getIntArray(String userInput){
        String []stringArray = userInput.split(", ");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            try {
                intArray[i] = Integer.parseInt(stringArray[i]);
            } catch(Exception error){
                return intArray;
            }
        }
        return intArray;
    }
    private static String getTrim(Scanner in) {
        return in.nextLine().replace("[", "").replace("]", "").trim();
    }
}
