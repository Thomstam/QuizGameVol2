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
            scoreToSaveInSinglePlayer(player);
        }else {
            scoreToSaveInMultiplayer(player);
        }
    }

    private void scoreToSaveInSinglePlayer(Player player) {
        try {
            File singlePlayerScoreFile = new File("SinglePlayerFileScore.txt");
            if (singlePlayerScoreFile.exists() && !singlePlayerScoreFile.isDirectory()) {
                FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String stringToBeWritten = formattedStringToWrite(player, positionForSingleGames);
                bufferedWriter.write("\n");
                bufferedWriter.write(stringToBeWritten);
                bufferedWriter.close();
                fileWriter.close();
            } else {
                if (singlePlayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt", true);
                    fileWriter.write(" \t\t\t\tSingle Player Winners\n\n");
                    fileWriter.write(" Winners Name\t\t\t\t\t\t\t\tScore\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, positionForSingleGames);
                    positionForSingleGames++;
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

    private void scoreToSaveInMultiplayer(Player player){
        try {
            File multiplayerScoreFile = new File("MultiplayerFileScore.txt");
            if (multiplayerScoreFile.exists() && !multiplayerScoreFile.isDirectory()) {
                FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String stringToBeWritten = formattedStringToWrite(player, positionForMultiplayerGames);
                positionForMultiplayerGames++;
                bufferedWriter.write("\n");
                bufferedWriter.write(stringToBeWritten);
                bufferedWriter.close();
                fileWriter.close();
            } else {
                if (multiplayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt");
                    fileWriter.write(" \t\t\t\t  Multiplayer Winners\n\n");
                    fileWriter.write(" Winners Name\t\t\t\t\t\t\t\tScore\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, positionForMultiplayerGames);
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

    private String formattedStringToWrite(Player player, int position){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(position).append(".").append(player.getUsername());
        for (int i = 0; i < 44 - player.getUsername().length() ; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append(":").append(player.getScore());
        return String.valueOf(stringBuilder);
    }
}
