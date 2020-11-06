
public class ChooseTheRight extends Gamemode {

    @Override
    void gamemodePlay() {
        System.out.println(toString(theArrayListWithTheQuestionAnswers));
        while (!"A".equals(choice) && !"B".equals(choice) && !"C".equals(choice) && !"D".equals(choice)){
            choice = scanner.nextLine();
        }
        int indexOfChoice = this.handle(choice);
        if (theArrayListWithTheQuestionAnswers.get(indexOfChoice).equals(correctAnswer)){
            System.out.println("congrats");
        }
    }

    @Override
    void handleTheScore(Player player) {

    }

}
