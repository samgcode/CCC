package src2018.Problem1;

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
        
        String filename = "s1.15";
        File file = new File("/Users/44146002/Documents/Code/CCC-master/data/2018/s1/" + filename + ".in");
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
        List<Integer> towns = parseInputs(data);
        Collections.sort(towns);
        // System.out.println(towns);
        double smallestTown = getSmallestTown(towns);
        System.out.printf("%.1f", smallestTown);
        // System.out.print(smallestTown);

    }

    public static List<Integer> parseInputs(List<String> inputString) {
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());

        return parsedInputs;
    }

    public static double getSmallestTown(List<Integer> towns) {
        double smallestTown = Double.MAX_VALUE;
        List<Double> sizes = new ArrayList<Double>();
        for(int town = 1; town < towns.size()-1; town++) {
            int leftTown = towns.get(town-1);
            int currentTown = towns.get(town);
            int rightTown = towns.get(town+1);
            sizes.add(getTownSize((double)leftTown, (double)currentTown, (double)rightTown));
        }
        for(int town = 1; town < towns.size()-1; town++) {
            double size = sizes.get(town-1);
            if(size < smallestTown) {
                smallestTown = (double)size;
            }
        }
        // System.out.println(sizes);
        return (double)smallestTown;
    }

    public static double getTownSize(double leftTown, double town, double rightTown) {
        double size = (double)(rightTown-leftTown)/2;
        return size;
    }
}
