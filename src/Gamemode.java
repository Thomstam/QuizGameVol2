import java.util.*;

public abstract class Gamemode {

    protected List<List<String>> mathArray;
    protected List<List<String>> generalKnowledgeArray;
    protected List<List<String>> scienceArray;
    protected List<List<String>> moviesArray;
    protected List<String> categories;
    protected String question;
    protected String correctAnswer;
    protected Scanner scanner;
    protected String choice;
    protected Random random;
    protected List<String> theArrayListWithTheQuestion;
    protected List<String> theArrayListWithTheQuestionAnswers;
    protected List<String> randomPicks;


    public Gamemode() {
        categories = Arrays.asList("Math", "Science", "General Knowledge","Movies");
        scanner = new Scanner(System.in);
        random = new Random();
        choice = "";
//        categories = Arrays.asList("Math", "Math");
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

    abstract void gamemodePlay();


    protected void gamemodeSetUp(){
        randomPicks = pickRandom(categories, 2);
        switch (randomPicks.get(0)) {
            case "Math":
                if (mathArray.isEmpty()){
                    initializeOfMathArray();
                }
                theArrayListWithTheQuestion = this.randomSelectedQuestion(mathArray);
                theArrayListWithTheQuestionAnswers = this.setUpQuestionTemplate(theArrayListWithTheQuestion);
                this.gamemodePlay();
                break;
            case "General Knowledge":
                if (generalKnowledgeArray.isEmpty()){
                    initializeOfGeneralKnowledgeArray();
                }
                theArrayListWithTheQuestion = this.randomSelectedQuestion(generalKnowledgeArray);
                theArrayListWithTheQuestionAnswers = this.setUpQuestionTemplate(theArrayListWithTheQuestion);
                this.gamemodePlay();
                break;
            case "Science":
                if (scienceArray.isEmpty()){
                    initializeOfScienceArray();
                }
                theArrayListWithTheQuestion = this.randomSelectedQuestion(scienceArray);
                theArrayListWithTheQuestionAnswers = this.setUpQuestionTemplate(theArrayListWithTheQuestion);
                this.gamemodePlay();
                break;
            case "Movies":
                if (moviesArray.isEmpty()){
                    initializeOfMoviesArray();
                }
                theArrayListWithTheQuestion = this.randomSelectedQuestion(moviesArray);
                theArrayListWithTheQuestionAnswers = this.setUpQuestionTemplate(theArrayListWithTheQuestion);
                this.gamemodePlay();
                break;
        }
    }
    protected static List<String> pickRandom(List<String> lst, int n) {
        List<String> copy = new LinkedList<String>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    protected List<String> setUpQuestionTemplate(List<String> listWithTheQuestion) {
        question = listWithTheQuestion.get(0);
        correctAnswer = listWithTheQuestion.get(1);
        listWithTheQuestion.remove(0);
        Collections.shuffle(listWithTheQuestion);
        return listWithTheQuestion;
    }

    public String toString(List<String> possibleAnswers) {
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

    protected List<String> randomSelectedQuestion(List<List<String>> categoryList) {
        return categoryList.get(random.nextInt(mathArray.size()));
    }

    abstract void handleTheScore(Player player);

}

