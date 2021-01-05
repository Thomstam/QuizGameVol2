package MainPackage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Categories {

    private List<Question> mathArray;
    private List<Question> generalKnowledgeArray;
    private List<Question> scienceArray;
    private List<Question> moviesArray;
    protected List<String> categories;
    private final Random random;

    public Categories() {
        mathArray = new LinkedList<>();
        generalKnowledgeArray = new LinkedList<>();
        scienceArray = new LinkedList<>();
        moviesArray = new LinkedList<>();
        categories = Arrays.asList("Math", "Science", "General Knowledge","Movies");
        random= new Random();
    }


    public void initializeTheArrayWithMathQuestions(){
        this.mathArray =  ReadingFromFile.fillingTheData("Math.txt");
    }

    public void initializeTheArrayWithScienceQuestions(){
        this.scienceArray =  ReadingFromFile.fillingTheData("Science.txt");
    }

    public void initializeTheArrayWithGeneralKnowledgeQuestions(){
        this.generalKnowledgeArray =  ReadingFromFile.fillingTheData("General Knowledge.txt");
    }

    public void initializeTheArrayWithMoviesQuestions(){
        this.moviesArray =  ReadingFromFile.fillingTheData("Movies.txt");
    }

    public List<Question> getGeneralKnowledgeArray() {
        return generalKnowledgeArray;
    }

    public List<Question> getMoviesArray() {
        return moviesArray;
    }

    public List<Question> getMathArray() {
        return mathArray;
    }

    public List<Question> getScienceArray() {
        return scienceArray;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getRandomCategory(){
        return categories.get(random.nextInt(categories.size()));
    }
}
