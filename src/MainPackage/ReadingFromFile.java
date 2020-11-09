package MainPackage;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;

import java.util.*;
import java.util.Scanner;// Import the Scanner class to read text files


public class ReadingFromFile {

    public static List<Question> fillingTheData(String nameOfFile){
        List<Question> arrayWithAllTheQuestion = new ArrayList<>(1);
        try {
            File myObj = new File(nameOfFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataOfStr = data.split("[,/]", 5);
                dataOfStr[0] =dataOfStr[0].trim();
                Question question = new Question();
                question.setQuestionToASk(dataOfStr[0]);
                question.setCorrectAnswer(dataOfStr[1]);
                for (int i = 2; i < dataOfStr.length; i++) {
                    question.setPossibleAnswers(dataOfStr[i]);
                }
                arrayWithAllTheQuestion.add(question);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return arrayWithAllTheQuestion;
    }

}
