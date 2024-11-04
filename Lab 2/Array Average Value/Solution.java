import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public double average(int[] array) {
        int sum = 0;
        for (int j : array) sum += j;
        return (double) sum / array.length;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String userInput = getTrim(in);
        int []intArray = getIntArray(userInput);
        Solution solutionObject = new Solution();
        double average = solutionObject.average(intArray);
        System.out.println(average);
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