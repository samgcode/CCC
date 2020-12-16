package Problem5;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Hashtable;
import java.io.*;
import java.lang.Thread;

public class Main {
    public static void main( String[] args )
    {  
        List<String> data = new ArrayList<String>();
        
        String filename = "s5.156";
        File file = new File("/workspaces/2017/data/s5/" + filename + ".in");
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

        String firstLineStr = data.get(0);
        List<Integer> firstLine = parseInput(firstLineStr);
        String secondLineStr = data.get(1);
        List<Integer> secondLine = parseInput(secondLineStr);
        String thirdLineStr = data.get(2);
        List<Integer> thirdLine = parseInput(thirdLineStr);

        int numStations = firstLine.get(0);
        int numLines = firstLine.get(1);
        int numActions = firstLine.get(2);

        List<Integer> people = new ArrayList<Integer>(thirdLine);

        List<List<Integer>> lines = getLines(numLines, secondLine);

        List<Station> stations = getStations(lines, people);

        List<String> instructionsStr = new ArrayList<String>(data.subList(3, data.size()));
        List<List<Integer>> instructions = parseInstructions(instructionsStr);
        
        executeInstructions(stations, instructions);
    }

    public static List<Integer> parseInput(String input) {
        List<String> inputString = Arrays.asList(input.split(" "));
        List<Integer> parsedInputs = inputString.stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());

        return parsedInputs;
    }

    public static List<List<Integer>> parseInstructions(List<String> instructionsStr) {
        List<List<Integer>> parsedInstructions = instructionsStr.stream().map(instruction -> parseInput(instruction)).collect(Collectors.toList());
        return parsedInstructions;
    }
    
    public static List<List<Integer>> getLines(int numLines, List<Integer> data) {
        List<List<Integer>> lines = new ArrayList<List<Integer>>();
        for(int i = 0; i < numLines; i++) {
            lines.add(new ArrayList<Integer>());
        }
        
        for(int station = 0; station < data.size(); station++) {
            Integer line = data.get(station);
            lines.get(line-1).add(station+1);
        }

        return lines;
    }

    public static List<Station> getStations(List<List<Integer>> lines, List<Integer> people) {
        List<Station> stations = new ArrayList<Station>();

        for(int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            List<Integer> line = lines.get(lineIndex);
            for(int stationIndex = 0; stationIndex < line.size(); stationIndex++) {
                int station = line.get(stationIndex);
                int nextStation = 0;
                if(stationIndex+1 < line.size()) {
                    nextStation = line.get(stationIndex+1);
                } else {
                    nextStation = line.get(0);
                }
                
                Station newStation = new Station();
                newStation.station = station;
                newStation.nextStation = nextStation;
                newStation.line = lineIndex + 1;
                newStation.people = 0;
                stations.add(newStation);
            }
        }
        

        for(int stationIndex = 0; stationIndex < stations.size(); stationIndex++) {
            Station currentStation = stations.get(stationIndex);
            int station = currentStation.station;
            stations.get(stationIndex).people = people.get(station-1);
        }

        return stations;
    }

    public static List<Hashtable<String, Integer>> sortHashtables(List<Hashtable<String, Integer>> stations) {
        List<Hashtable<String, Integer>> sortedStations = new ArrayList<Hashtable<String, Integer>>(stations);
        int count = 0;
        while(!checkOrder(sortedStations)) {
            System.out.println("start: " + count);
            List<Hashtable<String, Integer>> tempStations = new ArrayList<Hashtable<String, Integer>>(stations);
            for(int station = 0; station < stations.size(); station++) {
                if(station > 0) {
                    Hashtable<String, Integer> prevStation = sortedStations.get(station-1);
                    Hashtable<String, Integer> currentStation = sortedStations.get(station);
                    int prevStationInt = prevStation.get("station");
                    int currentStationInt = currentStation.get("station");
                    if(currentStationInt < prevStationInt) {
                        Collections.swap(tempStations, station-1, station);
                    }
                }
            }
            sortedStations = new ArrayList<Hashtable<String, Integer>>(tempStations);
            count++;
        }
        System.out.println("end: " + count);
        return sortedStations;
    }

    public static boolean checkOrder(List<Hashtable<String, Integer>> stations) {
        for(int station = 0; station < stations.size(); station++) {
            if(station > 0) {
                Hashtable<String, Integer> prevStation = stations.get(station-1);
                Hashtable<String, Integer> currentStation = stations.get(station);
                int prevStationInt = prevStation.get("station");
                int currentStationInt = currentStation.get("station");
                if(currentStationInt < prevStationInt) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void executeInstructions(List<Station> inputStations, List<List<Integer>> instructions) {
        List<Station> stations = new ArrayList<Station>(inputStations);
        for(int instIndex = 0; instIndex < instructions.size(); instIndex++) {
            List<Integer> instruction = instructions.get(instIndex);
            if(instruction.get(0) == 1) {
                InstructionThread thread = new InstructionThread(); 
                thread.start();
                int people = thread.conductSurvey(stations, instruction.get(1), instruction.get(2));
                System.out.println(people);
            } else {
                int line = instruction.get(1);
                stations = operateLine(stations, line);
            }
        }
    }

    public static List<Station> operateLine(List<Station> stations, int line) {
        List<Station> tempStations = new ArrayList<Station>();
        for(int i = 0; i < stations.size(); i++) {
            tempStations.add(new Station(stations.get(i)));
        }

        for(int stationIndex = stations.size()-1; stationIndex >= 0; stationIndex--) {
            Station firstStation = stations.get(stationIndex);
            if(firstStation.line == line) {
                int secondStationIndex = firstStation.nextStation - 1;
                Station currentStation = stations.get(stationIndex);
                int currentStationPeople = currentStation.people;
                int tempStationIndex = 0;
                for(int i = 0; i < tempStations.size(); i++) {
                    Station tempStation = tempStations.get(i);
                    if(tempStation.station-1 == secondStationIndex) {
                        tempStationIndex = i;
                        break;
                    }
                }
                tempStations.get(tempStationIndex).people = currentStationPeople;
            }
        }

        return tempStations;
    }

}

class Station {
    int station;
    int nextStation;
    int line;
    int people;

    public Station() {
    }

    public Station(Station newStation) {
        station = newStation.station;
        nextStation = newStation.nextStation;
        line = newStation.line;
        people = newStation.people;
    }

    public String toString() {
        return ("[station: " + station + ", next station: " + nextStation + ", line: " + line + ", people: " + people + "]");
    }
}

class InstructionThread extends Thread {
    public void run() {
        try {
            System.out.println ("Thread " + Thread.currentThread().getId() + " is running");
        } catch (Exception err) { 
            System.out.println ("Exception is caught"); 
            System.out.println(err);
        } 
    }

    public static int conductSurvey(List<Station> stations, int min, int max) {
        int people = 0;
        for(int stationIndex = 0; stationIndex < stations.size(); stationIndex++) {
            Station station = stations.get(stationIndex);
            int stationNumber = station.station;
            if(stationNumber >= min && stationNumber <= max) {
                people += station.people;
            }
        }
        return people;
    }
}