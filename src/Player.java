import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

    private String username;
    private int score;
    private int bet;

    public Player(String username){

        this.username=username;
        this.score=0;
        this.bet=0;

    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){this.username=username;}

    public int getScore(){return this.score;}

    public void setScore(int score){this.score=score;}

    public int getBet() { return this.bet; }

    public void setBet(int bet) { this.bet = bet; }

    public String pickAnswer(){

        String answer=null;

        Scanner scanner = new Scanner(System.in);
        do {
            try {
                answer= scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Wrong Input!!!");
            }

        }while(!(answer.equals("A") && answer.equals("B") && answer.equals("C") && answer.equals("D")));

        return answer;

    }




}
