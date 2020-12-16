package MainPackage;
import java.io.*;


public class ScoreSaving {

    public ScoreSaving(){}

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
                    String stringToBeWritten = formattedStringToWriteForTheFirstTime(player);
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
                    String stringToBeWritten = formattedStringToWriteForTheFirstTime(player);
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

    private String formattedStringToWrite(Player player, int typeOfTheGame) throws IOException {
        StringBuilder stringBuilder= new StringBuilder();
        if (typeOfTheGame == 1){
            stringBuilder.append(countLinesOld("SinglePlayerFileScore.txt") - 1).append(".").append(player.getUsername());
        }else {
            stringBuilder.append(countLinesOld("MultiplayerFileScore.txt") - 1).append(".").append(player.getUsername());
        }
        for (int i = 0; i < 44 - player.getUsername().length() ; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append(":").append(player.getScore());
        return String.valueOf(stringBuilder);
    }

    private String formattedStringToWriteForTheFirstTime(Player player){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(1).append(".").append(player.getUsername());
        for (int i = 0; i < 44 - player.getUsername().length() ; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append(":").append(player.getScore());
        return String.valueOf(stringBuilder);
    }


    public static int countLinesOld(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        }
    }
}
