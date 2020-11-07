
public class PointBuilder extends Gamemode {

    @Override
    void gamemodePlay() {
        System.out.println(toString(theArrayListWithTheQuestionAnswers));
        while (!"A".equals(choice) && !"B".equals(choice) && !"C".equals(choice) && !"D".equals(choice)){
            choice = scanner.nextLine();
        }
        int indexOfChoice = this.handle(choice);
        if (theArrayListWithTheQuestionAnswers.get(indexOfChoice).equals(correctAnswer)){




        }
    }

    @Override
    void handleTheScore(Player player) {
        player.setScore(player.getScore() + 1000);
    }

}
