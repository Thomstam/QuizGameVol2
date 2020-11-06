import java.util.*;

public abstract class Gamemode {

    protected List<List<String>> mathArray;
    protected List<List<String>> generalKnowledgeArray;
    protected List<List<String>> scienceArray;
    protected List<List<String>> movies;
    protected List<String> categories;
    protected String question;
    protected String correctAnswer;
    protected Scanner scanner;
    protected String choice;
    public Gamemode(){
//        categories = Arrays.asList("Math", "Science", "General Knowledge","Movies");
        scanner = new Scanner(System.in);
        choice = "";
        categories = Arrays.asList("Math", "Math");
        mathArray = new LinkedList<>();
        generalKnowledgeArray= new LinkedList<>();
        scienceArray = new LinkedList<>();
        movies = new LinkedList<>();
    }

    protected void initializeOfMathArray(){
        mathArray = ReadingFromFile.fillingTheData("Math.txt");
    }

    protected void initializeOfScienceArray(){
        scienceArray = ReadingFromFile.fillingTheData("Science.txt");
    }

    protected void initializeOfGeneralKnowledgeArray(){
        generalKnowledgeArray = ReadingFromFile.fillingTheData("General Knowledge.txt");
    }

    protected void initializeOfMoviesArray(){
        movies = ReadingFromFile.fillingTheData("Movies.txt");
    }

    abstract void gamemodePlay();

    public static List<String> pickNRandom(List<String> lst, int n) {
        List<String> copy = new LinkedList<String>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    protected void setUpQuestionTemplate(List<String> questionTemplate){
        question = questionTemplate.get(0);
        correctAnswer = questionTemplate.get(1);
    }

    public String toString(List<String> possibleAnswers){
        return "        " +question+ "\n"+
                "A)"+possibleAnswers.get(0)+"                                                    "+"B)"+possibleAnswers.get(1)+"\n"+
                "C)"+possibleAnswers.get(2)+"                                                    "+"D)"+possibleAnswers.get(3)+"\n"+
                "           "+"Choose Between A,B,C,D"  ;
    }

    protected int handle(String choice){
        switch (choice){
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
}

