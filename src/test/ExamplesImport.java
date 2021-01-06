package test;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class ExamplesImport {

    public ExamplesImport(){}

    public Question getQuestionToExample(){
        Question question = new Question();
        question.setQuestionToASk("10 squared equals:");
        question.setCorrectAnswer("100");
        question.setPossibleAnswers("1000");
        question.setPossibleAnswers("10000");
        question.setPossibleAnswers("100000");
        question.setPossibleAnswersToAsk();
        return question;
    }

    public ArrayList<Player> getPlayersToExample(Question question){
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("thomas");
        Player player2 = new Player("kostas");
        players.add(player1);
        players.add(player2);
        switch (question.getPossibleAnswersToAsk().indexOf("100")){
            case 0:
                player1.setAnswer("A");
                player2.setAnswer("D");
                break;
            case 1:
                player1.setAnswer("B");
                player2.setAnswer("C");
                break;
            case 2:
                player1.setAnswer("C");
                player2.setAnswer("B");
                break;
            case 3:
                player1.setAnswer("D");
                player2.setAnswer("A");
                break;
        }
        return players;
    }
}