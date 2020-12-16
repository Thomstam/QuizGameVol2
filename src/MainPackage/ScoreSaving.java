package MainPackage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ScoreSaving {

    private int positionForSingleGames;
    private int positionForMultiplayerGames;

    public ScoreSaving(){
        positionForSingleGames = 1;
        positionForMultiplayerGames = 1;
    }

    public void scoreToSave(Player player, int typeOfTheGame){
        if (typeOfTheGame == 1){
            scoreToSaveInSinglePlayer(player, typeOfTheGame);
        }else {
            scoreToSaveInMultiplayer(player, typeOfTheGame);
        }
    }

    private void scoreToSaveInSinglePlayer(Player player, int typeOfTheGame) {
        try {
            File singlePlayerScoreFile = new File("SinglePlayerFileScore.txt");
            if (singlePlayerScoreFile.exists() && !singlePlayerScoreFile.isDirectory()) {
                FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String stringToBeWritten = formattedStringToWrite(player, typeOfTheGame);
                bufferedWriter.write("\n");
                bufferedWriter.write(stringToBeWritten);
                bufferedWriter.close();
                fileWriter.close();
            } else {
                if (singlePlayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt", true);
                    fileWriter.write(" \t\t\t\tSingle Player Winners\n\n");
                    fileWriter.write(" Winners Name\t\t\t\t\t\t\t\t  Score\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, typeOfTheGame);
                    bufferedWriter.write(stringToBeWritten);
                    bufferedWriter.close();
                    fileWriter.close();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void scoreToSaveInMultiplayer(Player player, int typeOfTheGame){
        try {
            File multiplayerScoreFile = new File("MultiplayerFileScore.txt");
            if (multiplayerScoreFile.exists() && !multiplayerScoreFile.isDirectory()) {
                FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String stringToBeWritten = formattedStringToWrite(player, typeOfTheGame);
                bufferedWriter.write("\n");
                bufferedWriter.write(stringToBeWritten);
                bufferedWriter.close();
                fileWriter.close();
            } else {
                if (multiplayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt");
                    fileWriter.write(" \t\t\t\t  Multiplayer Winners\n\n");
                    fileWriter.write(" Winners Name\t\t\t\t\t\t\t\t  Score\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, typeOfTheGame);
                    bufferedWriter.write(stringToBeWritten);
                    bufferedWriter.close();
                    fileWriter.close();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private String formattedStringToWrite(Player player, int typeOfTheGame){
        StringBuilder stringBuilder= new StringBuilder();
        if (typeOfTheGame == 1){
            stringBuilder.append(positionForSingleGames).append(".").append(player.getUsername());
            positionForSingleGames++;
        }else {
            stringBuilder.append(positionForMultiplayerGames).append(".").append(player.getUsername());
            positionForMultiplayerGames++;
        }
        for (int i = 0; i < 44 - player.getUsername().length() ; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append(":").append(player.getScore());
        return String.valueOf(stringBuilder);
    }


}
