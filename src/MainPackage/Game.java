package MainPackage;

import MainPackage.Gamemodes.Betting;
import MainPackage.Gamemodes.Gamemode;
import MainPackage.Gamemodes.PointBuilder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {

    private int howManyPlayers;
    private int numberOfRounds;
    private int numberOfQuestions;
    private ArrayList<Player> players;


    public  Game(int howManyPlayers,int numberOfRounds,int numberOfQuestions){

        this.howManyPlayers=howManyPlayers;
        this.numberOfRounds=numberOfRounds;
        this.numberOfQuestions=numberOfQuestions;
        players=new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for(int i=0;i<howManyPlayers;i++){

            System.out.format("Enter Username (Player %d):",i);
            String username = scanner.nextLine();
            players.add(new Player(username));
        }

    }

    public void start(){
        int choice;


        for(int i=0;i<numberOfRounds;i++){
            System.out.println("Press ENTER to start the round!!!");
            try {
                System.in.read();
            }catch (Exception e){}
            choice=gamemodePicker();
            if(choice==0){
                System.out.println("POINTBUILDER : Choose the correct answer and win 1000 points!");
                Gamemode gamemode1 = new PointBuilder();
                gamemode1.gamemodeSetUp(players,numberOfQuestions);
            }else if(choice==1){
                System.out.println("BETTING");
                Gamemode gamemode2=new Betting();
                gamemode2.gamemodeSetUp(players,numberOfQuestions);
            }

        }

    }

    private int gamemodePicker(){

        Random rand = new Random();
        int choice = rand.nextInt(2);
        return(choice);

    }
}
