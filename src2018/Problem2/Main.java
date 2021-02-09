package src2018.Problem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main( String[] args )
    {  
        List<String> data = new ArrayList<String>();
        
        // String filename = "s2.02";
        // File file = new File("/workspaces/2017/data/2018/J4S2/" + filename + ".in");
        // try {
            // Scanner myReader = new Scanner(file);
            Scanner myReader = new Scanner(new InputStreamReader(System.in));
            while (myReader.hasNextLine()) {

            // for(int i = 0; i < 3; i++) {
                data.add(myReader.nextLine());
            }
            myReader.close();
        // } catch (FileNotFoundException e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }

        List<List<Integer>> inputs = parseInputs(data);
        List<List<Integer>> fixedData = fixData(inputs);
        printArray(fixedData);
        
    }



    public static void printArray(List<List<Integer>> array) {
        for (List<Integer> list : array) {
            for (int value : list) {
                System.out.print(value + " ");
            }
            System.out.println("");
        }
    }

    public static List<Integer> parseInput(String input) {
        List<String> inputString = Arrays.asList(input.split(" "));
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());

        return parsedInputs;
    }

    public static List<List<Integer>> parseInputs(List<String> data) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        for(int i = 1; i < data.size(); i++) {
            output.add(parseInput(data.get(i)));
        }

        return output;
    }

    public static List<List<Integer>> rotateArray(List<List<Integer>> array) {
        List<List<Integer>> rotatedArray = new ArrayList<List<Integer>>();
        for(int y = 0; y < array.size(); y++) {
            rotatedArray.add(new ArrayList<Integer>());
            for(int x = 0; x < array.get(y).size(); x++) {
                int originalY = array.size() - y - 1;
                int originalVal = array.get(x).get(originalY);
                rotatedArray.get(y).add(x, originalVal);
            }
        }

        return rotatedArray;
    }

    public static List<List<Integer>> fixData(List<List<Integer>> data) {
       List<List<Integer>> fixedData = new ArrayList<List<Integer>>(data);
        while(!isFixed(fixedData)) {
            fixedData = rotateArray(fixedData);
        }

        return fixedData;
    }

    public static boolean isFixed(List<List<Integer>> data) {
        int prevValue = 0;
        for(int i = 0; i < data.get(0).size(); i++) {
            int value = data.get(0).get(i);
            if(value < prevValue) {
                return false;
            }
            prevValue = value;
        }
        prevValue = 0;
        for(int i = 0; i < data.size(); i++) {
            int value = data.get(i).get(0);
            if(value < prevValue) {
                return false;
            }
            prevValue = value;
        }

        return true;
    }
}
