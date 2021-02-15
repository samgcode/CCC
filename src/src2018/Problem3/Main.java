package src2018.Problem3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;


public class Main {
    public static void main( String[] args )
    {  
        List<String> data = new ArrayList<String>();
        
        // String filename = "s3.sample-01";
        String filename = "s3.0-02";
        File file = new File("/Users/44146002/Documents/Code/CCC-master/data/2018/S3/" + filename + ".in");
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

        List<Integer> firstLine = parseInput(data.get(0));
        data.remove(0);
        List<List<String>> grid = seperateInputs(data);
        // System.out.print(grid);
        findPathTo(0, 1, grid);
        
    }

    public static List<Integer> parseInput(String input) {
        List<String> inputString = Arrays.asList(input.split(" "));
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());

        return parsedInputs;
    }
    
    public static List<List<String>> seperateInputs(List<String> inputs) {
        List<List<String>> output = new ArrayList<List<String>>();
        for(int index = 1; index < inputs.size()-1; index++) {
            String line = inputs.get(index);
            line = line.substring(1, line.length()-1);
            List<String> inputString = Arrays.asList(line.split(""));
            output.add(new ArrayList<String>(inputString));
        }

        return output;
    }

    public static List<Integer> getStart(List<List<String>> grid) {
        List<Integer> startPos = new ArrayList<Integer>();
        startPos.add(2);
        startPos.add(3);
        return startPos;
    }

    public static Integer findPathTo(int x, int y, List<List<String>> grid) {
        int minPath = 0;
        List<Integer> startPos = getStart(grid);
        int startX = startPos.get(0);
        int startY = startPos.get(1);
        List<List<Integer>> weights = new ArrayList<List<Integer>>();
        for(int i = 0; i < grid.size(); i++) {
            List<Integer> maxList = new ArrayList<Integer>();
            for(int q = 0; q < grid.get(i).size(); q++) {
                if(i == startY && q == startX) {
                    maxList.add(1);
                }
                // maxList.add(Integer.MAX_VALUE);
                maxList.add(100000);
            }
            weights.add(new ArrayList<Integer>(maxList));
        }
        System.out.print(weights);
        updateNeighborWeights(2, 3, grid, weights);
        System.out.print(weights);


        return minPath;
    }

    public static void updateNeighborWeights(int x, int y, List<List<String>> grid, List<List<Integer>> weights) {
        int weight = weights.get(y).get(x);
        String tile = grid.get(y).get(x);
        if(x < grid.get(0).size()) { //right
            weights.get(y).set(x+1, getTileWeightAt(x+1, y, grid, weight));
        }
        if(x > 0) {//left
            weights.get(y).set(x-1, getTileWeightAt(x-1, y, grid, weight));
        }

        if(x < grid.get(0).size()) { //down
            weights.get(y+1).set(x, getTileWeightAt(x, y+1, grid, weight));
        }
        if(x > 0) {//up
            weights.get(y-1).set(x, getTileWeightAt(x, y-1, grid, weight));
        }
    }

    public static Integer getTileWeightAt(int x, int y, List<List<String>> grid, int prevWeight) {
        String tile = grid.get(y).get(x);

        if(tile.matches(".*[CW].*")) {
            //tile is wall
            return Integer.MAX_VALUE;
        }
        
        if(tile.matches(".*[UDLR].*")) {
            //tile is conveyor
            return prevWeight;
        }

        //tile is empty
        return prevWeight + 1;
    }
}

/* todo:
    - check for camera line of sight
*/