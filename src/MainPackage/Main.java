package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        root= FXMLLoader.load(getClass().getResource("resources/Welcome.fxml"));
        stage = new Stage();
        stage.setTitle("New Game");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

//        Scanner scanner= new Scanner(System.in);
//
//        boolean endGame=true;
//        do{
//            //user input for the number of players that want to play
//            int howManyPlayers=0;
//            do{
//                try {
//                    System.out.println("Press '1' for One-Player-Mode!");
//                    howManyPlayers = scanner.nextInt();
//                    if (howManyPlayers!=1){
//                        System.out.println("Wrong Input!!!");
//                    }
//                }catch (InputMismatchException e){
//                    System.out.println("Wrong Input!!!");
//                }
//                scanner.nextLine();
//            }while(howManyPlayers!=1);
//
//
//
//            //user input for the number of rounds that will be played
//            int numberOfRounds = 0;
//            do {
//                try {
//                    System.out.println("How many rounds do you want to play?");
//                    numberOfRounds= scanner.nextInt();
//                }catch (InputMismatchException e){
//                    System.out.println("Wrong Input!!!");
//                }
//                scanner.nextLine();
//            }while(numberOfRounds<=0);
//
//
//            //user input for the number of questions tha each round will have
//            int numberOfQuestions=0;
//            do {
//                try {
//                    System.out.println("How many questions do you want to play per round?");
//                    numberOfQuestions= scanner.nextInt();
//                }catch (InputMismatchException e){
//
//                    System.out.println("Wrong Input!!!");
//                }
//                scanner.nextLine();
//            }while((numberOfQuestions<3 || numberOfQuestions>6));
//
//
//            Game game = new Game(howManyPlayers,numberOfRounds,numberOfQuestions);
//            game.start();
//            endGame=game.end();
//        }while(!endGame);



    }



}
