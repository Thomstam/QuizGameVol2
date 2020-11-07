import java.util.ArrayList;

public class PointBuilder extends Gamemode {

    @Override
    void gamemodePlay(ArrayList<Player> players) {
        System.out.println(toString(theArrayListWithTheQuestionAnswers));
        for (Player player : players) {
            while (!"A".equals(choice) && !"B".equals(choice) && !"C".equals(choice) && !"D".equals(choice)){
                choice = scanner.nextLine();
            }
            player.setAnswer(choice);
            choice = "";
        }
        for (Player player : players) {
            int indexOfChoice = handle(player.getAnswer());
            if(theArrayListWithTheQuestionAnswers.get(indexOfChoice).equals(correctAnswer)){
                  this.handleTheScore(player);
            }
        }
    }

    @Override
    void handleTheScore(Player player) { player.setScore(player.getScore() + 1000);}

}
