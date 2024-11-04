import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public int[] moveValue(int[] array, int value) {
        int []returnedArray = new int[array.length];
        int index = 0;

        for (int i = 0; i < array.length; i++){
            if (value != array[i]) {
                returnedArray[index] = array[i];
                index++;
            }
        }
        while (index < array.length){
            returnedArray[index] = value;
            index++;
        }
        return returnedArray;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int condition = 0;
        String userInput = getTrim(in);
        if (userInput.equals("")){
            condition = 1;
            userInput = Arrays.toString(new int[0]);
            System.out.println(userInput);
        }
        if (condition == 0) {
            int value = in.nextInt();
            int[] intArray = getIntArray(userInput);
            Solution solutionObject = new Solution();
            intArray = solutionObject.moveValue(intArray, value);
            System.out.print('[');
            int arrayLength = intArray.length;
            for (int i = 0; i < arrayLength; i++) {
                if (i != arrayLength - 1) System.out.print(intArray[i] + ", ");
                else System.out.print(intArray[i]);
            }
            System.out.print(']');
        }
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
