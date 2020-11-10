package MainPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);

        boolean endGame=true;
        do{
            //user input for the number of players that want to play
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



            //user input for the number of rounds that will be played
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


            //user input for the number of questions tha each round will have
            int numberOfQuestions=0;
            do {
                try {
                    System.out.println("How many questions do you want to play per round?");
                    numberOfQuestions= scanner.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Wrong Input!!!");
                }
                scanner.nextLine();
            }while((numberOfQuestions<3 || numberOfQuestions>6));


            Game game = new Game(howManyPlayers,numberOfRounds,numberOfQuestions);

            game.start();
            endGame=game.end();
        }while(!endGame);



    }
}
