package src2017.Problem2;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main( String[] args )
    {  
        List<String> data = new ArrayList<String>();
        
        // String filename = "s2.sample.01";
        // File file = new File("/workspaces/2017/data/s2/" + filename + ".in");
        // try {
        //     Scanner myReader = new Scanner(file);
            Scanner myReader = new Scanner(new InputStreamReader(System.in));
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            myReader.close();
        // } catch (FileNotFoundException e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }

        String stringMesurements = data.get(1);

        List<Integer> measurements = parseInput(stringMesurements);
        List<Integer> sortedMeasurements = new ArrayList<Integer>(measurements);
        
        Collections.sort(sortedMeasurements);

        int lowTidesLength = getLowTidesLength(measurements);

        List<Integer> lowTides = getLowTides(lowTidesLength, sortedMeasurements, measurements);
        List<Integer> highTides = getHighTides(lowTidesLength, sortedMeasurements, measurements);
        
        List<Integer> orderedMeasurements = orderMeasurments(lowTidesLength, highTides, lowTides);

        String output = orderedMeasurements.toString();
        String formatedOutput = output.replace(",", "");
        formatedOutput = formatedOutput.replace("[", "");
        formatedOutput = formatedOutput.replace("]", "");
        System.out.println(formatedOutput);
    }

    public static List<Integer> parseInput(String input) {
        List<String> inputString = Arrays.asList(input.split(" "));
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());


        return parsedInputs;
    }

    public static int getLowTidesLength(List<Integer> measurements) {
        Double halfLength = ((double)measurements.size())/2;
        Integer lowTidesLength = (int)(Math.ceil(halfLength));
        
        return lowTidesLength;
    }

    public static List<Integer> getLowTides(int lowTidesLength, List<Integer> sortedMeasuremnts, List<Integer> measurements) {
        List<Integer> lowTides = new ArrayList<Integer>();
        int highest = sortedMeasuremnts.get(lowTidesLength-1);
        for (Integer tide : measurements) {
            if(tide <= highest) {
                lowTides.add(tide);
            }
        }
        Collections.sort(lowTides);
        Collections.reverse(lowTides);

        return lowTides;
    }

    public static List<Integer> getHighTides(int lowTidesLength, List<Integer> sortedMeasuremnts, List<Integer> measurements) {
        List<Integer> highTides = new ArrayList<Integer>();
        int lowest = sortedMeasuremnts.get(lowTidesLength);
        for (Integer tide : measurements) {
            if(tide >= lowest) {
                highTides.add(tide);
            }
        }
        Collections.sort(highTides);
        return highTides;
    }

    public static List<Integer> orderMeasurments(int halfCount, List<Integer> highTides, List<Integer> lowTides) {
        List<Integer> orderedMeasurements = new ArrayList<Integer>();

        for(int tide = 0; tide < halfCount; tide++) {
            orderedMeasurements.add(lowTides.get(tide));
            orderedMeasurements.add(highTides.get(tide));
        }

        return orderedMeasurements;
    }
    
}
