package MainPackage.Gamemodes;

import java.util.*;
import java.util.ArrayList;
import MainPackage.Player;
import MainPackage.Question;
import MainPackage.Categories;

public abstract class Gamemode {

    protected Scanner scanner;
    protected Random random;
    protected Question questionToBeHandled;

    public Gamemode() {
        scanner = new Scanner(System.in);
        random = new Random();
        questionToBeHandled = new Question();
    }

    /**
     * Managing the gamemode initialization. For each question we choose a random category. If the list of
     * the category we choose is empty we initialize and we fill it from a txt full of questions.
     * Then we just pass it to @setUpQuestion and then we start the game.
     * @param categories
     * @param categoriesToAsk
     */
    public Question gamemodeSetUp(Categories categories,String categoriesToAsk){
            Question questionToBeAsked = null;
            switch (categoriesToAsk) {
                case "Math":
                    if (categories.getMathArray().isEmpty()){
                        categories.initializeTheArrayWithMathQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getMathArray());
                    break;
                case "General Knowledge":
                    if (categories.getGeneralKnowledgeArray().isEmpty()){
                        categories.initializeTheArrayWithGeneralKnowledgeQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getGeneralKnowledgeArray());

                    break;
                case "Science":
                    if (categories.getScienceArray().isEmpty()){
                        categories.initializeTheArrayWithScienceQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getScienceArray());
                    break;
                case "Movies":
                    if (categories.getMoviesArray().isEmpty()){
                        categories.initializeTheArrayWithMoviesQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getMoviesArray());
                    break;
            }
            return questionToBeAsked;
        }

    /**
     * @param choice The choice we got from the player
     * @return We return an int that points to the @getPossibleAnswersToAsk position
     *         so we can check if the given answer is the same with the correct one.
     */
    protected int handlePlayerChoice(String choice) {
        switch (choice) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            default:
                return 5;
        }
    }

    /**
     * We apply the score for each gamemode separately.
     * @param players List with all the players.
     * @param question The question we asked.
     */
    abstract void handleTheScore(ArrayList<Player> players, Question question);


    /**
     *We take the a list from a specific category which contains all the questions.
     * We pick one in random and we pass it to @questionToBeHandled to take care of the rest.
     * Then we delete it so it doenst come up again.
     * @param questionsList Is the list of question from a particular category.
     */
    protected Question setUpQuestion(List<Question> questionsList){
        int indexOfQuestion = random.nextInt(questionsList.size());
        questionToBeHandled =  questionsList.get(indexOfQuestion);
        questionsList.remove(indexOfQuestion);
        return questionToBeHandled;
    }

    public void callHandleTheScore(ArrayList<Player> players, Question question){
        handleTheScore(players,question);
    }
}