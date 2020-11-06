import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Press '1' for One-Player-Mode or '2' for Two-Player-Mode!");
        String howManyPlayers = scanner.nextLine();


        System.out.println("How many rounds do you want to play?");
        String numberOfRounds = scanner.nextLine();

        Game game = new Game(Integer.parseInt(howManyPlayers),Integer.parseInt(numberOfRounds));

    }
}
