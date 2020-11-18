package MainPackage.Gamemodes;

import java.util.*;
import java.util.ArrayList;
import MainPackage.Player;
import MainPackage.Question;
import MainPackage.Categories;

public abstract class Gamemode {

    protected Scanner scanner;
    protected String choice;
    protected Random random;
    protected String categoriesToAsk;
    protected int initialBet;
    protected Question questionToBeHandled;

    public Gamemode() {
        scanner = new Scanner(System.in);
        random = new Random();
        questionToBeHandled = new Question();
    }

    /**
     * We control each game mode's difference.
     * @param players List with all the players
     * @return
     */
    abstract Question gamemodePlay(ArrayList<Player> players, Question question);

    /**
     * Managing the gamemode initialization. For each question we choose a random category. If the list of
     * the category we choose is empty we initialize and we fill it from a txt full of questions.
     * Then we just pass it to @setUpQuestion and then we start the game.
     * @param players <String> elements with the names of all the players.
     * @param numberOfQuestions The numbers of questions we going to ask for this round.
     */
    public Question gamemodeSetUp(ArrayList<Player> players, int numberOfQuestions, Categories categories){
        //for (int i = 0; i < numberOfQuestions; i++) {
            categoriesToAsk = categories.getRandomCategory();
            Question questionToBeAsked ;
            Question question = null;
            System.out.format("The category is %s\n",categoriesToAsk);
            switch (categoriesToAsk) {
                case "Math":
                    if (categories.getMathArray().isEmpty()){
                        categories.initializeTheArrayWithMathQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getMathArray());
                    question=gamemodePlay(players, questionToBeAsked);
                    break;
                case "General Knowledge":
                    if (categories.getGeneralKnowledgeArray().isEmpty()){
                        categories.initializeTheArrayWithGeneralKnowledgeQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getGeneralKnowledgeArray());
                    question=gamemodePlay(players, questionToBeAsked);
                    break;
                case "Science":
                    if (categories.getScienceArray().isEmpty()){
                        categories.initializeTheArrayWithScienceQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getScienceArray());
                    question=gamemodePlay(players, questionToBeAsked);
                    break;
                case "Movies":
                    if (categories.getMoviesArray().isEmpty()){
                        categories.initializeTheArrayWithMoviesQuestions();
                    }
                    questionToBeAsked = this.setUpQuestion(categories.getMoviesArray());
                    question=gamemodePlay(players, questionToBeAsked);
                    break;
            }
            return question;
        }
        //scoreSumUp(players);
    //}

    /**
     * @param question The current question of the game.
     * @return A template of the how we are showing the player each question and his choices.
     */
    protected String toString(Question question) {
        List<String> possibleAnswers = question.getPossibleAnswersToAsk();
        return "        " + question.getQuestionToASk() + "\n" +
                "A)" + possibleAnswers.get(0) + "                                                    " + "B)" + possibleAnswers.get(1) + "\n" +
                "C)" + possibleAnswers.get(2) + "                                                    " + "D)" + possibleAnswers.get(3) + "\n" +
                "           " + "Choose Between A,B,C,D";
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
     * Each players has to choose between the possible answers of the question A-D.
     * @param players List with all the players.
     * @return List with all the players choices updated
     */
    protected ArrayList<Player> settingPlayersChoice(ArrayList<Player> players){
        for (Player player : players) {
            do{
                try {
                    choice = scanner.nextLine();
                    if (!"A".equals(choice) && !"B".equals(choice) && !"C".equals(choice) && !"D".equals(choice)){
                        System.out.println("Wrong Input!!!");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Wrong Input!!!");
                    System.out.println("Choose Between A,B,C,D");
                }
            }while(!"A".equals(choice) && !"B".equals(choice) && !"C".equals(choice) && !"D".equals(choice));
            player.setAnswer(choice);
            choice = "";
        }
        return players;
    }

    /**
     * Here we present each player's score
     * @param players List with all the players
     */
    protected void scoreSumUp(ArrayList<Player> players){
        for (Player player: players) {
            System.out.println("The Player " + player.getUsername() + " has total points of " + player.getScore());
        }
    }

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
}