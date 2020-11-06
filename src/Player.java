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
        System.out.format("Choose a Gamemode (Player %d): \n1.ChooseTheRight\n2.StopTheClock\n",player);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();;
        while(!(choice.equals("1")||choice.equals("2"))){
            System.out.println("Wrong Input!!!");
            System.out.format("Choose a Gamemode (Player %d): \n1.ChooseTheRight\n2.StopTheClock\n",player);
            choice = scanner.nextLine();
        }

        if (choice.equals("1")){
            Gamemode gamemode = new ChooseTheRight();
            gamemode.gamemodePlay();
        }else if (choice.equals("2")){

        }

    }


}
