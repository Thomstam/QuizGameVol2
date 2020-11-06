
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChooseTheRight extends Gamemode {

    @Override
    void gamemodePlay() {
        List<String> theArrayListWithThequestion;
        Random random = new Random();
        List<String> randomPicks = pickNRandom(categories, 2);
        switch (randomPicks.get(0)) {
            case "Math":
                if (mathArray.isEmpty()){
                    initializeOfMathArray();
                }
                int indexOfArrayQuestion = random.nextInt(mathArray.size());
                theArrayListWithThequestion = mathArray.get(indexOfArrayQuestion);
                this.setUpQuestionTemplate(theArrayListWithThequestion);
                theArrayListWithThequestion.remove(0);
                Collections.shuffle(theArrayListWithThequestion);
                System.out.println(toString(theArrayListWithThequestion));
                while (!"A".equals(choice) && !"B".equals(choice) && !"C".equals(choice) && !"D".equals(choice)){
                    choice = scanner.nextLine();
                }
                int indexOfChoice = this.handle(choice);
                if (theArrayListWithThequestion.get(indexOfChoice).equals(correctAnswer)){
                    System.out.println("congrats");
                }
                break;
            case "General Knowledge":
                if (generalKnowledgeArray.isEmpty()){
                    initializeOfMathArray();
                }
                break;
            case "Science":
                if (scienceArray.isEmpty()){
                    initializeOfMathArray();
                }
                break;
            case "Movies":
                if (movies.isEmpty()){
                    initializeOfMathArray();
                }
                break;
        }
    }



}
