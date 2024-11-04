import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public int[][] transpose(int[][] array) {
        int rows = array.length;
        int [][]transposedArray = new int[rows][rows];
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                transposedArray[i][j] = array[j][i];
            }
        }
        return transposedArray;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String []stringArray = replaceAndSplit(userInput);
        boolean gotIn = false;
        int [][]intArray = toInt(stringArray);
        Solution solutionObject = new Solution();
//        [[1, 1], [2, 3]]
        intArray = solutionObject.transpose(intArray);
        System.out.print("[");
        for (int i = 0; i < intArray.length; i++){
            gotIn = true;
            System.out.print("[");
            for (int j = 0; j < intArray[i].length - 1; j++) System.out.print(intArray[i][j] + ", ");
            System.out.print(intArray[i][intArray[i].length - 1] + "]");
            if (i != intArray.length - 1) System.out.print(", ");
        }
        if (gotIn) System.out.print("]");
        else{
            System.out.print("[]]");
        }
    }
    public static String[] replaceAndSplit(String userInput){
        return userInput.replaceAll("\\[\\[|\\]\\]", "").split("\\], \\[");
    }

    public static int[][] toInt(String[] stringArray){
        if (Objects.equals(stringArray[0], "")) {
            return new int[0][];
        }
        int[][] intArray = new int[stringArray.length][];
        for (int i = 0; i < stringArray.length; i++) {
            String[] numbers = stringArray[i].split(", ");
            intArray[i] = new int[numbers.length];
            for (int j = 0; j < numbers.length; j++) {
                intArray [i][j] = Integer.parseInt(numbers[j]);
            }
        }
        return intArray;
    }
}
