import java.util.ArrayList;
import java.util.List;

public abstract class Gamemode {

    protected List<List<String>> mathArray;
    protected List<List<String>> generalKnowledgeArray;
    protected List<List<String>> scienceArray;
    protected String[] categories;

    public Gamemode(){
        categories = new String[]{"Math", "Science", "General Knowledge"};
        mathArray = null;
        generalKnowledgeArray= null;
        scienceArray = null;
    }

    protected void initializeOfMathArray(){
        mathArray = ReadingFromFile.fillingTheData("Math.txt");
    }

    abstract void gamemodePlay();
}

