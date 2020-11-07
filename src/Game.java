import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int howManyPlayers;
    private int numberOfRounds;
    private ArrayList<Player> players;


    public  Game(int howManyPlayers,int numberOfRounds){

        this.howManyPlayers=howManyPlayers;
        this.numberOfRounds=numberOfRounds;
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
        int numOfPlayers=players.size();
        int playercounter=0;

        for(int i=0;i<numberOfRounds;i++){
            System.out.println("Press ENTER to start the round!!!");
            try {
                System.in.read();
            }catch (Exception e){}
            if(playercounter<numOfPlayers) {
                choice = players.get(playercounter).chooseGamemode(playercounter);
                playercounter++;
            }else{
                playercounter=0;
                choice = players.get(playercounter).chooseGamemode(playercounter);
                playercounter++;
            }

            switch (choice){
                case 1:
                    //Gamemode gamemode = new ChooseTheRight();
                    //gamemode.gamemodeSetUp();
                case 2:

            }

        }



    }



}
