import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Press '1' for One-Player-Mode!");
        int howManyPlayers = scanner.nextInt();
        while(howManyPlayers!=1){
            System.out.println("Wrong Input!!!");
            System.out.println("Press '1' for One-Player-Mode or '2' for Two-Player-Mode!");
            howManyPlayers = scanner.nextInt();
        }


        System.out.println("How many rounds do you want to play?");
        int numberOfRounds = scanner.nextInt();

        Game game = new Game(howManyPlayers,numberOfRounds);
        game.start();

//        Gamemode gamemode = new ChooseTheRight();
//        gamemode.gamemodeSetUp();
    }
}
