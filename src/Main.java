import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        int howManyPlayers=0;
        do{
            try {
                System.out.println("Press '1' for One-Player-Mode!");
                howManyPlayers = scanner.nextInt();
                if (howManyPlayers!=1){
                    System.out.println("Wrong Input!!!");
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong Input!!!");
            }
            scanner.nextLine();
        }while(howManyPlayers!=1);



        int numberOfRounds = 0;
        do {
            try {
                System.out.println("How many rounds do you want to play?");
                numberOfRounds= scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Wrong Input!!!");
            }
            scanner.nextLine();
        }while(numberOfRounds<=0);

        Game game = new Game(howManyPlayers,numberOfRounds);
        game.start();

//        Gamemode gamemode = new ChooseTheRight();
//        gamemode.gamemodeSetUp();
    }
}
