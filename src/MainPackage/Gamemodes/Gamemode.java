package MainPackage.Gamemodes;

import java.util.*;
import java.util.ArrayList;
import MainPackage.Player;
import MainPackage.Question;
import MainPackage.ReadingFromFile;

public abstract class Gamemode {

    protected List<Question> mathArray;
    protected List<Question> generalKnowledgeArray;
    protected List<Question> scienceArray;
    protected List<Question> moviesArray;
    protected List<String> categories;
    protected List<String> theArrayListWithThePossibleAnswers;
    protected String question;
    protected String correctAnswer;
    protected Scanner scanner;
    protected String choice;
    protected Random random;
    protected String categoriesToAsk;
    protected int initialBet;
    protected Question questionToBeHandled;

    public Gamemode() {
//        categories = Arrays.asList("Math", "Science", "General Knowledge","Movies");
        scanner = new Scanner(System.in);
        random = new Random();
        categories = Arrays.asList("Math", "Math");
        mathArray = new LinkedList<>();
        generalKnowledgeArray = new LinkedList<>();
        scienceArray = new LinkedList<>();
        moviesArray = new LinkedList<>();
        questionToBeHandled = new Question();
    }

    /**
     * @param fileToRead The file with all the questions.
     * @return A List of Lists. Each List has a list which contains the question with all the possible answers.
     */
    protected List<Question> initializeTheArrayWithTheQuestions(String fileToRead){ return ReadingFromFile.fillingTheData(fileToRead); }

    /**
     * We control each game mode's difference.
     * @param players List with all the players
     */
    abstract void gamemodePlay(ArrayList<Player> players, Question question);

    /**
     * Managing the gamemode initialization. For each question we choose a random category, it handles if it is empty,
     * Then we choose one random position from the List of the category we chose and copy it into a new List(X).
     * The copied List(X) contains 5 String elements.The question and the 4 possible answers.
     * Then we sent the List(X) to @setUpTheQuestionTemplate to extract the data for the question.
     * We remove the question we got from the List with all the question so we dont get it again and we start the game.
     * @param players <String> elements with the names of all the players.
     * @param numberOfQuestions The numbers of questions we going to ask for this round.
     */
    public void gamemodeSetUp(ArrayList<Player> players, int numberOfQuestions){
        for (int i = 0; i < numberOfQuestions; i++) {
            int indexOfQuestion;
            categoriesToAsk = categories.get(random.nextInt(categories.size()));
            switch (categoriesToAsk) {
                case "Math":
                    if (mathArray.isEmpty()){
                        mathArray = initializeTheArrayWithTheQuestions("Math.txt");
                    }
                    indexOfQuestion = random.nextInt(mathArray.size());
                    questionToBeHandled = mathArray.get(indexOfQuestion);
                    mathArray.remove(indexOfQuestion);
                    gamemodePlay(players, questionToBeHandled);
                    break;
                case "General Knowledge":
                    if (generalKnowledgeArray.isEmpty()){
                        generalKnowledgeArray = initializeTheArrayWithTheQuestions("General Knowledge.txt");
                    }
                    indexOfQuestion = random.nextInt(mathArray.size());
                    questionToBeHandled = mathArray.get(indexOfQuestion);
                    generalKnowledgeArray.remove(indexOfQuestion);
                    gamemodePlay(players, questionToBeHandled);
                    break;
                case "Science":
                    if (scienceArray.isEmpty()){
                        scienceArray = initializeTheArrayWithTheQuestions("Science.txt");
                    }
                    indexOfQuestion = random.nextInt(mathArray.size());
                    questionToBeHandled = mathArray.get(indexOfQuestion);
                    scienceArray.remove(indexOfQuestion);
                    gamemodePlay(players, questionToBeHandled);
                    break;
                case "Movies":
                    if (moviesArray.isEmpty()){
                        moviesArray = initializeTheArrayWithTheQuestions("Movies.txt");
                    }
                    indexOfQuestion = random.nextInt(mathArray.size());
                    questionToBeHandled = mathArray.get(indexOfQuestion);
                    moviesArray.remove(indexOfQuestion);
                    gamemodePlay(players, questionToBeHandled);
                    break;
            }
//            resetTheArraysForQuestionTemplate();
        }
        scoreSumUp(players);
    }


    protected String toString(Question question) {
        List<String> possibleAnswers = question.getPossibleAnswersToAsk();
        return "        " + question.getQuestionToASk() + "\n" +
                "A)" + possibleAnswers.get(0) + "                                                    " + "B)" + possibleAnswers.get(1) + "\n" +
                "C)" + possibleAnswers.get(2) + "                                                    " + "D)" + possibleAnswers.get(3) + "\n" +
                "           " + "Choose Between A,B,C,D";
    }

    /**
     * @param choice The choice we got from the player
     * @return We return an int that points to the @theArrayWithTheQuestion position that now only contains only the answers,
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
     */
    abstract void handleTheScore(ArrayList<Player> players, Question question);

    /**
     * At the end of round we clear the List with the question we made so we can take the next question from
     * the next rounds.
     */
//    private void resetTheArraysForQuestionTemplate(){
//        theArrayListWithThePossibleAnswers.clear();}

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



}