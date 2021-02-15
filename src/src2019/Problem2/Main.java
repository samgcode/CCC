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
        List<Integer> input = parseInstructions(data);
        print(getAllPrimes(input));
    }

    public static List<Integer> parseInput(String input) {
        List<String> inputString = Arrays.asList(input.split(" "));
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());

        return parsedInputs;
    }

    public static List<Integer> parseInstructions(List<String> inputStr) {
        List<Integer> parsedInputs = inputStr.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());
        return parsedInputs;
    }

    public static List<List<Integer>> getAllPrimes(List<Integer> input) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();

        for(int i = 0; i < input.size(); i++) {
            output.add(getPrimes(input.get(i)));
        }

        return output;
    }

    public static List<Integer> getPrimes(Integer number) {
        List<Integer> primes = new ArrayList<Integer>();
        for(int i = 2; i < number; i++) {
            if(isPrime(i)) {
                int q = (number * 2) - i;
                if(isPrime(q)) {
                    int average = (i + q) / 2;
                    if(number.equals(average)) {
                        primes.add(i);
                        primes.add(q);
                        break;
                    }
                }
            }
        }
        return primes;
    }


    public static void print(List<List<Integer>> array) {
        for(int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i).get(0) + " ");
            System.out.println(array.get(i).get(1));
        }
    }

    public static boolean isPrime(int number) {
        return number > 1 
          && IntStream.rangeClosed(2, (int) Math.sqrt(number))
          .noneMatch(n -> (number % n == 0));
    }
    
}
