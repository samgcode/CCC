package src2019.Problem2;

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
        
        String filename = "s2.1-02";
        File file = new File("/workspaces/CCC/data/2019/s2/" + filename + ".in");
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

        data.remove(0);
        List<Integer> input = parseInputs(data);

    }

    public static List<Integer> parseInput(String input) {
        List<String> inputString = Arrays.asList(input.split(" "));
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());

        return parsedInputs;
    }

    public static List<Integer> parseInputs2ByN(List<String> inputStr) {
        List<Integer> parsedInputs = inputStr.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());
        return parsedInputs;
    }

    public static List<List<Integer>> parseInputs(List<String> instructionsStr) {
        List<List<Integer>> parsedInstructions = instructionsStr.stream().map(instruction -> parseInput(instruction)).collect(Collectors.toList());
        return parsedInstructions;
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
