package MainPackage;


public class Player {

    private String username;
    private double score;
    private int bet;
    private String answer;
    private String placement;
    private boolean scoreAchieved;
    private int timeLeftFromAnswer;
    private boolean isTheAnswerCorrect;
    private int numberOfCorrectAnswers;


    public Player(String username){
        this.isTheAnswerCorrect=false;
        this.username=username;
        this.score=0;
        this.numberOfCorrectAnswers=0;

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

    public void setTimeLeftFromAnswer(int timeLeft) { this.timeLeftFromAnswer=timeLeft; }

    public int getTimeLeftFromAnswer() {
        return timeLeftFromAnswer;
    }

    //public void resetArrayWithTimLeft(){this.timeLeftFromEachAnswer.clear();}

    public void setIsTheAnswerCorrect(boolean isTheAnswerCorrect){
        this.isTheAnswerCorrect=isTheAnswerCorrect;
    }

    public boolean getIsTheAnswerCorrect(){
        return isTheAnswerCorrect;
    }

    public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers){this.numberOfCorrectAnswers=numberOfCorrectAnswers;}

    public int getNumberOfCorrectAnswers(){return numberOfCorrectAnswers;}
}
