package Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main( String[] args )
    {   
        List<String> data = new ArrayList<String>();

        // String filename = "s1.09";
        // File file = new File("/workspaces/2017/data/s1/" + filename + ".in");
        // Scanner myReader = new Scanner(file);
        Scanner myReader = new Scanner(new InputStreamReader(System.in));
        while (myReader.hasNextLine()) {
            data.add(myReader.nextLine());
        }
        myReader.close();

        String inputDays = data.get(0);
        String inputLine2 = data.get(1);
        String inputLine3 = data.get(2);

        int days = Integer.parseInt(inputDays);
        List<Integer> swRuns = parseRuns(inputLine2);
        List<Integer> smRuns = parseRuns(inputLine3);
        
        int output = getEqualDay(days, swRuns, smRuns);
        System.out.println(output);
    }

    public static List<Integer> parseRuns(String input) {
        List<String> teamRunsString = Arrays.asList(input.split(" "));
        List<Integer> teamRuns = teamRunsString.stream().map(run -> Integer.parseInt(run)).collect(Collectors.toList());

        return teamRuns;
    }

    public static int getEqualDay(int days, List<Integer> swRuns, List<Integer> smRuns) {
        int swTotal = 0;
        int smTotal = 0;
        int maxDay = 0;
        for(int day = 0; day < days; day++) {
            swTotal += swRuns.get(day);
            smTotal += smRuns.get(day);
            if(swTotal == smTotal) {
                maxDay = day + 1;
            }
        }

        return maxDay;
    }
}
