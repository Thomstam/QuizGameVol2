package MainPackage;
import java.io.*;
import java.util.Scanner;


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
                String stringToBeWritten = formattedStringToWrite(player, typeOfTheGame, 0);
                bufferedWriter.write("\n");
                bufferedWriter.write(stringToBeWritten);
                bufferedWriter.close();
                fileWriter.close();
            } else {
                if (singlePlayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt");
                    fileWriter.write(" \t\t\t\tSingle Player Winners\n\n");
                    fileWriter.write("  Winners Name\t\t\t\t\t\t\t\t  Score\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWriteForTheFirstTime(player, typeOfTheGame);
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
                if (checkIfTheUserNameExists(player)){

                }else{
                    FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, typeOfTheGame, 1);
                    bufferedWriter.write(stringToBeWritten);
                    bufferedWriter.close();
                    fileWriter.close();
                }

            } else {
                if (multiplayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt");
                    fileWriter.write(" \t\t\t\t  Multiplayer Winners\n\n");
                    fileWriter.write("  Winners Name\t\t\t\t\t\t\t\t  Wins\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWriteForTheFirstTime(player, typeOfTheGame);
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

    private String formattedStringToWrite(Player player, int typeOfTheGame, int  wins) throws IOException {
        StringBuilder stringBuilder= new StringBuilder();
        if (typeOfTheGame == 1){
            stringBuilder.append(countLinesOld("SinglePlayerFileScore.txt") - 1).append(".").append(player.getUsername());
        }else {
            stringBuilder.append(countLinesOld("MultiplayerFileScore.txt") - 1).append(".").append(player.getUsername());
        }
        for (int i = 0; i < 44 - player.getUsername().length() ; i++) {
            stringBuilder.append(" ");
        }
        if (typeOfTheGame == 1){
            stringBuilder.append(":").append(player.getScore());
        }else {
            stringBuilder.append(":").append(wins);
        }
        return String.valueOf(stringBuilder);
    }

    private String formattedStringToWriteForTheFirstTime(Player player, int typeOfGame){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(1).append(".").append(player.getUsername());
        for (int i = 0; i < 44 - player.getUsername().length() ; i++) {
            stringBuilder.append(" ");
        }
        if (typeOfGame == 1){
            stringBuilder.append(":").append(player.getScore());
        }else {
            stringBuilder.append(":").append(1);
        }
        return String.valueOf(stringBuilder);
    }

    public static int countLinesOld(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars;
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

    private void replaceLines(String lineToChance, int wins, Player player, int position) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("MultiplayerFileScore.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            String theLineOfTheFileAfterModification = modifyTheStringToWrite(position, player, wins + 1 );
            while ((line = file.readLine()) != null) {
                if (line.equals(lineToChance)){
                    line = theLineOfTheFileAfterModification;
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');

            }
            file.close();


            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("MultiplayerFileScore.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }


    private String modifyTheStringToWrite(int position, Player player, int wins){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(position).append(".").append(player.getUsername());
        for (int i = 0; i < 44 - player.getUsername().length() ; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append(":").append(wins);
        return String.valueOf(stringBuilder);
    }

    private boolean checkIfTheUserNameExists(Player player) throws IOException {
        File file = new File("MultiplayerFileScore.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            String[] dataOfStr = data.split("[.:]", 3);
            if (dataOfStr.length > 1){
                dataOfStr[1] = dataOfStr[1].trim();
                if (dataOfStr[1].equals(player.getUsername())){
                    scanner.close();
                    int wins = Integer.parseInt(dataOfStr[2]);
                    int position = Integer.parseInt(dataOfStr[0]);
                    String originalStringOfTheFile = modifyTheStringToWrite(position, player, wins);
                    replaceLines(originalStringOfTheFile, wins, player, position);
                    return true;
                }
            }

        }
        return false;
    }
}
