import java.util.Scanner;

public class Game {

    private int howManyPlayers;
    private int numberOfRounds;
    private Player player;
    private Player player2;


    public  Game(int howManyPlayers,int numberOfRounds){

        this.howManyPlayers=howManyPlayers;
        this.numberOfRounds=numberOfRounds;

        if (howManyPlayers==1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Username:");
            String username = scanner.nextLine();
            player = new Player(username);
        }else if (howManyPlayers==2){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Username (Player 1):");
            String username = scanner.nextLine();
            player = new Player(username);

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Enter Username (Player 2):");
            String username2 = scanner2.nextLine();
            player2 = new Player(username2);

        }

        start();

    }

    public void printer(){

    }

    public void start(){
        int roundcounter = 0;
        while (roundcounter <numberOfRounds){
            if (howManyPlayers==1){
                player.chooseGamemode(1);
            }else if (howManyPlayers==2){
                if(roundcounter %2==0){
                    player.chooseGamemode(1);
                }else{
                    player2.chooseGamemode(2);

                }
            }
            roundcounter++;
        }
    }



}
