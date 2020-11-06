import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<List<String>> temp;
        temp = ReadingFromFile.fillingTheData("Math.txt");
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.get(i).size(); j++) {
                System.out.println(temp.get(i).get(j) + " ");
            }
        }
    }
}
