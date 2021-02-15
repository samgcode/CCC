package src2019.Problem3;

import java.util.ArrayList;
import java.util.stream.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.io.*;
import java.math.*;

public class Main {
    public static void main( String[] args )
    {  
        List<String> data = new ArrayList<String>();
        
        String filename = "s3.1-05";
        File file = new File("/workspaces/CCC/data/2019/s3/" + filename + ".in");
        try {
            Scanner myReader = new Scanner(file);
            // Scanner myReader = new Scanner(new InputStreamReader(System.in));
            while (myReader.hasNextLine()) {

            // for(int i = 0; i < 3; i++) {
                data.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        List<List<Integer>> input = parseInputs(data);
        // print(input);
        print(getSolution(input));
    }

    public static List<Integer> parseInput(String input) {
        List<String> inputString = Arrays.asList(input.split(" "));
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());

        return parsedInputs;
    }

    public static List<List<Integer>> parseInputs(List<String> inputsStr) {
        List<String> inputs = inputsStr.stream().map(input -> input.replace("X", Integer.toString(Integer.MAX_VALUE))).collect(Collectors.toList());
        List<List<Integer>> parsedInstructions = inputs.stream().map(instruction -> parseInput(instruction)).collect(Collectors.toList());
        return parsedInstructions;
    }

    public static List<List<Integer>> getSolution(List<List<Integer>> input) {
        for(int i = 0; i < 3; i++) {
            List<Integer> line = input.get(i);
            if(!isComplete(line)) {
                if(line.get(0).equals(Integer.MAX_VALUE)) {
                    if(!line.get(1).equals(Integer.MAX_VALUE)) {
                        if(!line.get(2).equals(Integer.MAX_VALUE)) {
                            line.set(0, line.get(1) - (line.get(2) - line.get(1)));
                        }
                    }
                }

                if(line.get(1).equals(Integer.MAX_VALUE)) {
                    if(!line.get(2).equals(Integer.MAX_VALUE)) {
                        line.set(1, line.get(0) + ((line.get(2) - line.get(0))/2));
                    }
                }

                if(line.get(2).equals(Integer.MAX_VALUE)) {
                    if(!line.get(0).equals(Integer.MAX_VALUE)) {
                        if(!line.get(1).equals(Integer.MAX_VALUE)) {
                            line.set(2, line.get(1) + (line.get(1) - line.get(0)));
                        }
                    }
                }
            }
        }


        return input;
    }

    public static boolean isComplete(List<Integer> input) {
        for(int i = 0; i < 3; i++) {
            if(input.get(i).equals(Integer.MAX_VALUE)) {
                return false;
            }
        }
        return true;
    }
 
    public static void print(List<List<Integer>> array) {
        for(int i = 0; i < array.size(); i++) {
            for(int q = 0; q < array.get(i).size(); q++) {
                System.out.print(array.get(i).get(q));
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
