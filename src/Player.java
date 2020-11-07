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

    public void setUsername(String username){this.username=username;}

    public int getScore(){return this.score;}

    public void setScore(int score){this.score=score;}

    public int chooseGamemode(int player){
        System.out.format("Choose a Gamemode (Player %d): \n1.ChooseTheRight\n2.StopTheClock\n",player);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();;
        while(!(choice==1||choice==2)){
            System.out.println("Wrong Input!!!");
            System.out.format("Choose a Gamemode (Player %d): \n1.ChooseTheRight\n2.StopTheClock\n",player);
            choice = scanner.nextInt();
        }

        return choice;

    }


}
