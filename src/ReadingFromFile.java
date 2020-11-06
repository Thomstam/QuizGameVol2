import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Scanner;// Import the Scanner class to read text files


public class ReadingFromFile {

    public static List<List<String>> fillingTheData(String nameOfFile){
        List<List<String> > aList = new ArrayList<>(1);
        ArrayList<String> temp = new ArrayList<>();
        try {
            File myObj = new File(nameOfFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataOfStr = data.split("[,/]", 5);
                dataOfStr[0] =dataOfStr[0].trim();
                temp.addAll(Arrays.asList(dataOfStr));
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (int i = 0; i < temp.size(); i = i + 5) {
            aList.add(temp.subList(i , i + 5));
        }
        return aList;
    }

}
