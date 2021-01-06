package MainPackage;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;

import java.util.*;
import java.util.Scanner;// Import the Scanner class to read text files


public class ReadingFromFile {

    /**
     * Here we open the given file. We read every line and we split each and every one them with a specific format that
     * we gave to the file so we can separate the question and then every possible answer for it.
     * From our format for the file we know that the question is going to be on the first position of each row following
     * of the correct answer and all the possible correct ones that we might use.
     * So for each row we create a new Object of Question so we can store the data and use them later.
     * @param nameOfFile Contains the name of the category we are going to get all the questions.
     * @return List of @Questions that we got from our file.
     */
    public static List<Question> fillingTheData(String nameOfFile){
        List<Question> arrayWithAllTheQuestion = new ArrayList<>(1);
        try {
            File myObj = new File(nameOfFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataOfStr = data.split("[,/]", 10);
                dataOfStr[0] =dataOfStr[0].trim();
                Question question = new Question();
                question.setQuestionToASk(dataOfStr[0]);
                question.setCorrectAnswer(dataOfStr[1]);
                for (int i = 2; i < dataOfStr.length; i++) {
                    if(dataOfStr[i].contains(".png") || dataOfStr[i].contains(".jpg")){
                        question.setNameOfImage(dataOfStr[i]);
                        question.setQuestionContainsImage();
                    }else {
                        question.setPossibleAnswers(dataOfStr[i]);
                    }
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
