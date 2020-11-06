import java.util.Scanner;

public class Player {

    private String username;
    private int score;

    public Player(String username){

        this.username=username;
        this.score=0;

    }

    public String getUsername(){
        return this.username;
    }

    public void chooseGamemode(int player){

        if (player==1){
            System.out.println("Choose a Gamemode (Player 1): \n1.ChooseTheRight\n2.StopTheClock");
        }else{
            System.out.println("Choose a Gamemode (Player 2): \n1.ChooseTheRight\n2.StopTheClock");
        }

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("1")){
            Gamemode gamemode = new ChooseTheRight();
            gamemode.gamemodePlay();
        }else if (choice.equals("2")){

        }

    }


}
