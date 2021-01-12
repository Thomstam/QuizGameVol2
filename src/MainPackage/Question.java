package MainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * In this class we set all the attributes of the question to be setup later.
 */
public class Question {

    private final List<String> possibleAnswers; //Contains all the wrong answers for the question.
    private String correctAnswer; //This is the correct answer for the question.
    private String questionToASk;
    private List<String> possibleAnswersToAsk; //Contains 3 of the possible answers and the correct one.
    private String nameOfImage;
    private boolean questionContainsImage;


    public Question(){
        possibleAnswers = new ArrayList<>();
        questionContainsImage = false;

    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setPossibleAnswers(String possibleAnswer) {
        possibleAnswers.add(possibleAnswer);
    }

    public void setQuestionToASk(String questionToASk) {
        this.questionToASk = questionToASk;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestionToASk() {
        return questionToASk;
    }

    /**
     * From all the possible answers that we might have for a question we pick 3 in random, we add the correct one
     * and then we shuffle so we dont get the same position of the answers every time.
     */
    public void setPossibleAnswersToAsk() {
        possibleAnswersToAsk = this.pickNRandom(possibleAnswers);
        possibleAnswersToAsk.add(this.getCorrectAnswer());
        Collections.shuffle(possibleAnswersToAsk);
    }

    /**
     * We check if the list with the possible question might have not been initialized and if so
     * we call the appropriate method to do so.
     * @return List with the possible answers we are going to show to the players
     */
    public List<String> getPossibleAnswersToAsk(){
        if(possibleAnswersToAsk == null){
            setPossibleAnswersToAsk();
        }
        return possibleAnswersToAsk;
    }

    /**
     * @param lst List contains all the possible answers to a question
     * @return 3 random (Wrong)answers from the list of possible answers for a specific question.
     */
    private List<String> pickNRandom(List<String> lst) {
        List<String> copy = new LinkedList<>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, 3);
    }

    public void setNameOfImage(String nameOfImage) {
        this.nameOfImage = nameOfImage;
    }

    public void setQuestionContainsImage(){
        questionContainsImage = true;
    }

    public String pathOfTheImage(){
        return "Images/" + nameOfImage;
    }

    public boolean isQuestionContainsImage(){ return  questionContainsImage;}

}
