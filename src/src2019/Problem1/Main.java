package src2019.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.io.*;

public class Main {
    public static void main( String[] args )
    {  
        List<String> data = new ArrayList<String>();
        
        String filename = "j4.06";
        File file = new File("/workspaces/CCC/data/2019/s1_j4/" + filename + ".in");
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


        List<String> input = splitInputs(data.get(0));
        List<Integer> amounts = getAmounts(input);
        print(flip(amounts));
    }

    public static List<String> splitInputs(String input) {
        List<String> inputString = Arrays.asList(input.split(""));

        return inputString;
    }

    public static List<Integer> getAmounts(List<String> input) {
        int h = 0;
        int v = 0;
        for(int i = 0; i < input.size(); i++) {
            String letter = input.get(i);
            if(letter.contains("H")) {
                h++;
            } else {
                v++;
            }
        }
        if(h % 2 == 0) {
            h = 0;
        } else {
            h = 1;
        }
        if(v % 2 == 0) {
            v = 0;
        } else {
            v = 1;
        }
        List<Integer> output = new ArrayList<Integer>();
        output.add(h);
        output.add(v);
        return output;
    }

    public static List<List<Integer>> flip(List<Integer> input) {
        List<List<Integer>> original = new ArrayList<List<Integer>>();
        List<Integer> first = new ArrayList<Integer>();
        List<Integer> second = new ArrayList<Integer>();
        first.add(1);
        first.add(2);
        second.add(3);
        second.add(4);
        original.add(new ArrayList<Integer>(first));
        original.add(new ArrayList<Integer>(second));
        // System.out.print(input);
        if(input.get(0).equals(0) && input.get(1).equals(0)) {
            return original;
        }

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if(input.get(0).equals(1)) {
            // System.out.print("h");
            output.add(new ArrayList<Integer>(original.get(1)));
            output.add(new ArrayList<Integer>(original.get(0)));
            original = new ArrayList<List<Integer>>(output);
        }
        // System.out.println(output);

        if(input.get(1).equals(1)) {
            if(output.size() >= 2) {
                output.remove(0);
                output.remove(0);
            }
            // System.out.print("v");
            List<Integer> out1 = new ArrayList<Integer>();
            List<Integer> in1 = original.get(0);
            out1.add(in1.get(1));
            out1.add(in1.get(0));
            List<Integer> out2 = new ArrayList<Integer>();
            List<Integer> in2 = original.get(1);
            out2.add(in2.get(1));
            out2.add(in2.get(0));
            output.add(new ArrayList<Integer>(out1));
            output.add(new ArrayList<Integer>(out2));
        }
        // System.out.println(output);

        return output;
    }

    public static void print(List<List<Integer>> flipped) {
        System.out.print(flipped.get(0).get(0) + " ");
        System.out.println(flipped.get(0).get(1));
        System.out.print(flipped.get(1).get(0) + " ");
        System.out.println(flipped.get(1).get(1));
    }
    
}
