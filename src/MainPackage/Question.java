package MainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Question {

    private List<String> possibleAnswers;
    private String correctAnswer;
    private String questionToASk;
    private List<String> possibleAnswersToAsk;

    public Question(){
        possibleAnswers = new ArrayList<>();
        possibleAnswers = new ArrayList<>();
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

    public void setPossibleAnswersToAsk() {
        possibleAnswersToAsk = this.pickNRandom(possibleAnswers);
        possibleAnswersToAsk.add(this.getCorrectAnswer());
        Collections.shuffle(possibleAnswersToAsk);
    }

    public List<String> getPossibleAnswersToAsk(){
        if(possibleAnswersToAsk == null){
            setPossibleAnswersToAsk();
        }
        return possibleAnswersToAsk;
    }

    private List<String> pickNRandom(List<String> lst) {
        List<String> copy = new LinkedList<>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, 3);
    }
}
