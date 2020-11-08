package MainPackage;

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

            System.out.format("Enter Username (MainPackage.Player %d):",i);
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
            switch (choice){
                case 0:
                    Gamemode gamemode = new PointBuilder();
                    gamemode.gamemodeSetUp(players,numberOfQuestions);
                case 1:


            }

        }

    }

    private int gamemodePicker(){

        Random rand = new Random();
        int choice = rand.nextInt(1);
        return(choice);

    }




}
