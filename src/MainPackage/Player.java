package MainPackage;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Player {

    private String username;
    private int score;
    private int bet;
    private String answer;


    public Player(String username){

        this.username=username;
        this.score=0;

    }

    public String getUsername(){ return this.username; }

    public void setUsername(String username){this.username=username;}

    public int getScore(){return this.score;}

    public void setScore(int score){this.score=score;}

    public int getBet() { return this.bet; }

    public void setBet(int bet) { this.bet = bet; }

    public String getAnswer() { return this.answer; }

    public void setAnswer(String answer) { this.answer = answer; }
}
