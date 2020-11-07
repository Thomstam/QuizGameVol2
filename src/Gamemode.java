import java.util.*;
import java.util.ArrayList;

public abstract class Gamemode {

    protected List<List<String>> mathArray;
    protected List<List<String>> generalKnowledgeArray;
    protected List<List<String>> scienceArray;
    protected List<List<String>> moviesArray;
    protected List<String> categories;
    protected List<String> theArrayListWithTheQuestion;
    protected List<String> theArrayListWithTheQuestionAnswers;
    protected String question;
    protected String correctAnswer;
    protected Scanner scanner;
    protected String choice;
    protected Random random;
    protected String categoriesToAsk;
    protected int initialBet;



    public Gamemode() {
//        categories = Arrays.asList("Math", "Science", "General Knowledge","Movies");
        scanner = new Scanner(System.in);
        random = new Random();
        choice = "";
        categories = Arrays.asList("Math", "Math");
        mathArray = new LinkedList<>();
        generalKnowledgeArray = new LinkedList<>();
        scienceArray = new LinkedList<>();
        moviesArray = new LinkedList<>();
    }

    protected void initializeOfMathArray() {
        mathArray = ReadingFromFile.fillingTheData("Math.txt");
    }

    protected void initializeOfScienceArray() {
        scienceArray = ReadingFromFile.fillingTheData("Science.txt");
    }

    protected void initializeOfGeneralKnowledgeArray() {
        generalKnowledgeArray = ReadingFromFile.fillingTheData("General Knowledge.txt");
    }

    protected void initializeOfMoviesArray() {
        moviesArray = ReadingFromFile.fillingTheData("Movies.txt");
    }

    abstract void gamemodePlay(ArrayList<Player> players);

    protected void gamemodeSetUp(ArrayList<Player> players, int numberOfQuestions){
        for (int i = 0; i < numberOfQuestions; i++) {
            int indexOfQuestion = 0;
            categoriesToAsk = categories.get(random.nextInt(categories.size()));
            switch (categoriesToAsk) {
                case "Math":
                    if (mathArray.isEmpty()){
                        initializeOfMathArray();
                    }
                    indexOfQuestion = random.nextInt(mathArray.size());
                    theArrayListWithTheQuestion = new ArrayList<>(mathArray.get(indexOfQuestion));
                    theArrayListWithTheQuestionAnswers = new ArrayList<>(theArrayListWithTheQuestion);
                    this.setUpQuestionTemplate(theArrayListWithTheQuestionAnswers);
                    gamemodePlay(players);
                    break;
                case "General Knowledge":
                    if (generalKnowledgeArray.isEmpty()){
                        initializeOfGeneralKnowledgeArray();
                    }
                    theArrayListWithTheQuestion = this.randomSelectedQuestion(generalKnowledgeArray);
                    this.setUpQuestionTemplate(theArrayListWithTheQuestionAnswers);
                    gamemodePlay(players);
                    break;
                case "Science":
                    if (scienceArray.isEmpty()){
                        initializeOfScienceArray();
                    }
                    theArrayListWithTheQuestion = this.randomSelectedQuestion(scienceArray);
                    this.setUpQuestionTemplate(theArrayListWithTheQuestionAnswers);
                    gamemodePlay(players);
                    break;
                case "Movies":
                    if (moviesArray.isEmpty()){
                        initializeOfMoviesArray();
                    }
                    theArrayListWithTheQuestion = this.randomSelectedQuestion(moviesArray);
                    this.setUpQuestionTemplate(theArrayListWithTheQuestionAnswers);
                    gamemodePlay(players);
                    break;
            }
            resetTheArraysForQuestionTemplate(indexOfQuestion);
        }
        scoreSumUp(players);
    }

    private void setUpQuestionTemplate(List<String> listWithTheQuestion) {
        question = listWithTheQuestion.get(0);
        correctAnswer = listWithTheQuestion.get(1);
        listWithTheQuestion.remove(0);
        Collections.shuffle(listWithTheQuestion);
    }

    protected String toString(List<String> possibleAnswers) {
        return "        " + question + "\n" +
                "A)" + possibleAnswers.get(0) + "                                                    " + "B)" + possibleAnswers.get(1) + "\n" +
                "C)" + possibleAnswers.get(2) + "                                                    " + "D)" + possibleAnswers.get(3) + "\n" +
                "           " + "Choose Between A,B,C,D";
    }

    protected int handle(String choice) {
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

    private List<String> randomSelectedQuestion(List<List<String>> categoryList) {
        return categoryList.get(random.nextInt(mathArray.size()));
    }

    abstract void handleTheScore(ArrayList<Player> players);

    private void resetTheArraysForQuestionTemplate(int indexOfQuestion){
        theArrayListWithTheQuestionAnswers.clear();
        theArrayListWithTheQuestion.clear();
        mathArray.remove(indexOfQuestion);
    }

    protected ArrayList<Player> settingPlayersChoice(ArrayList<Player> players){
        for (Player player : players) {
            while (!"A".equals(choice) && !"B".equals(choice) && !"C".equals(choice) && !"D".equals(choice)){
                choice = scanner.nextLine();
            }
            player.setAnswer(choice);
            choice = "";
        }
        return players;
    }

    protected void scoreSumUp(ArrayList<Player> players){
        for (Player player: players) {
            System.out.println("The Player " + player.getUsername() + " has total points of " + player.getScore());
        }
    }
}

