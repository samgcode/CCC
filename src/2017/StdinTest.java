import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StdinTest {
    public static void main (String[] args) {
        List<String> data = new ArrayList<String>();
        // BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner myReader = new Scanner(new InputStreamReader(System.in));
        // try {
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
        // } catch (IOException ioe) {
        //    System.out.println(ioe);
        // }

        System.out.println(data);
     }
}
