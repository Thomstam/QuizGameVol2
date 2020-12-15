package MainPackage;

import java.util.ArrayList;


public class Player {

    private String username;
    private double score;
    private int bet;
    private String answer;
    private String placement;
    private boolean scoreAchieved;
    private ArrayList<Integer> timeLeftFromEachAnswer;
    private boolean isTheAnswerCorrect;


    public Player(String username){
        this.isTheAnswerCorrect=false;
        this.username=username;
        this.score=0;
        timeLeftFromEachAnswer = new ArrayList<>();
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public double getScore(){
        return this.score;
    }

    public void setScore(double score){
        this.score=score;
    }

    public int getBet() {
        return this.bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPlacement(String placement){
        this.placement = placement;
    }

    public String getPlacement() {
        return placement;
    }

    public void setScoreAchieved(boolean scoreAchieved) {
        this.scoreAchieved = scoreAchieved;
    }

    public boolean getScoreAchieved() {
        return scoreAchieved;
    }

    public void setTimeLeftFromEachAnswer(int timeLeft) {
        this.timeLeftFromEachAnswer.add(timeLeft);
    }

    public ArrayList<Integer> getTimeLeftFromEachAnswer() {
        return timeLeftFromEachAnswer;
    }

    public void resetArrayWithTimLeft(){
        this.timeLeftFromEachAnswer.clear();
    }

    public void setIsTheAnswerCorrect(boolean isTheAnswerCorrect){
        this.isTheAnswerCorrect=isTheAnswerCorrect;
    }
    public boolean getIsTheAnswerCorrect(){
        return isTheAnswerCorrect;
    }
}
