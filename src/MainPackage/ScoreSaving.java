package MainPackage;
import java.io.*;
import java.util.Scanner;

/**
 * This class formats for both of Score texts strings to read and write the results.
 */
public class ScoreSaving {

    public void scoreToSave(Player player, int typeOfTheGame) {
        if (typeOfTheGame == 1) {
            scoreToSaveInSinglePlayer(player);
        } else {
            scoreToSaveInMultiplayer(player);
        }
    }

    /**
     * This method is separated in two parts. The first is the creation of the file in it doesnt
     * exists and the second to modify it as should. In the creation of the file we start in a specific
     * template and adds the first player to the file. The second part check's if a player already have
     * played and he check's his score to see if has achieved higher.
     * @param player A players to write in the txt file.
     */
    private void scoreToSaveInSinglePlayer(Player player) {
        try {
            File singlePlayerScoreFile = new File("SinglePlayerFileScore.txt");
            if (singlePlayerScoreFile.exists() && !singlePlayerScoreFile.isDirectory()) {
                if (checkIfTheUserNameExists(player, 1, "SinglePlayerFileScore.txt")) {

                } else {
                    FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, 1, 0, countLinesOld("SinglePlayerFileScore.txt"));
                    bufferedWriter.write("\n");
                    bufferedWriter.write(stringToBeWritten);
                    bufferedWriter.close();
                    fileWriter.close();
                }

            } else {
                if (singlePlayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt");
                    fileWriter.write(" \t\t\t\tSingle Player Winners\n");
                    fileWriter.write("  Winners Name\t\t\t\t\t\t\t\t  Score\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, 1, 0, 1);
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


    /**
     * This method is separated in two parts. The first is the creation of the file in it doesnt
     * exists and the second to modify it as should. In the creation of the file we start in a specific
     * template and adds the first player to the file. The second part check's if a player already have
     * played and he check's his score to see if has achieved higher and adds a win to his total.
     * @param player A players to write in the txt file.
     */
    private void scoreToSaveInMultiplayer(Player player) {
        try {
            File multiplayerScoreFile = new File("MultiplayerFileScore.txt");
            if (multiplayerScoreFile.exists() && !multiplayerScoreFile.isDirectory()) {
                if (checkIfTheUserNameExists(player, 2, "MultiplayerFileScore.txt")) {

                } else {
                    FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("\n");
                    String stringToBeWritten = formattedStringToWrite(player, 2, 1, countLinesOld("MultiplayerFileScore.txt"));
                    bufferedWriter.write(stringToBeWritten);
                    bufferedWriter.close();
                    fileWriter.close();
                }

            } else {
                if (multiplayerScoreFile.createNewFile()) {
                    FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt");
                    fileWriter.write(" \t\t\t\t  Multiplayer Winners\n");
                    fileWriter.write("  Winners Name\t\t\t\t\t\t\t\t  Wins\t\t\t\t\t\t\t\tScore\n");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, 2, 1, 1);
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

    /**
     * This method gets the players username length so it can append a specific amount of whitespaces
     * in order for all the players and sections of all the lines to start on the same position.
     * @param player The player to write to the file
     * @param typeOfTheGame To specify if it is Multiplayer if Single player
     * @param wins only applies to to Multiplayer mode how increase his total wins
     * @param position of the file that the string is going to be written
     * @return a string, a whole line, to write in the file.
     */
    private String formattedStringToWrite(Player player, int typeOfTheGame, int wins, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(position).append(".").append(player.getUsername());
        stringBuilder.append(" ".repeat(Math.max(0, 44 - player.getUsername().length())));
        if (typeOfTheGame != 1) {
            stringBuilder.append(":").append(wins);
            stringBuilder.append(" ".repeat(Math.max(0, 32)));
        }
        stringBuilder.append(":").append(player.getScore());
        return String.valueOf(stringBuilder);
    }


    /**
     *
     * @param filename The file to read from(Single player or Multiplayer)
     * @return the number of the lines of the file.
     * @throws IOException if the file not found.
     */
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

    /**
     * Here we change the line that the player who already played found to change his wins and score.
     * @param lineToChance The line that the player found.
     * @param wins the wins of the player to increase.
     * @param player the player to get his username.
     * @param position the position of the file to change the line.
     * @param score the score of the player.
     * @param nameOfTheFile Single player or Multiplayer.
     * @param typeOfTheGame Single player or Multiplayer.
     */
    private void replaceLines(String lineToChance, int wins, Player player, int position, double score, String nameOfTheFile, int typeOfTheGame) {
        try {

            BufferedReader file = new BufferedReader(new FileReader(nameOfTheFile));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            int lineOfTheFile = countLinesOld(nameOfTheFile);
            int counter = 0;
            String theLineOfTheFileAfterModification;
            theLineOfTheFileAfterModification = modifyTheStringToWrite(typeOfTheGame, position, player, wins + 1, score);
            while ((line = file.readLine()) != null && counter < lineOfTheFile + 1) {
                if (line.equals(lineToChance)) {
                    line = theLineOfTheFileAfterModification;
                }
                inputBuffer.append(line);
                if (counter != lineOfTheFile) {
                    inputBuffer.append('\n');
                }
                counter++;
            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(nameOfTheFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    /**
     * @param typeOfTheGame Single player or Multiplayer.
     * @param position the position of the file to change the line.
     * @param player the player to get his username.
     * @param wins the wins of the player to increase.
     * @param score the score of the player.
     * @return a changed line that is going to be written to the file.
     */
    private String modifyTheStringToWrite(int typeOfTheGame, int position, Player player, int wins, double score) {
        if (typeOfTheGame == 1) {
            return position + "." + player.getUsername() +
                    " ".repeat(Math.max(0, 44 - player.getUsername().length())) +
                    ":" + score;
        } else {
            return position + "." + player.getUsername() +
                    " ".repeat(Math.max(0, 44 - player.getUsername().length())) +
                    ":" + wins + " ".repeat(Math.max(0, 33 - String.valueOf(wins).length())) + ":" + score;
        }
    }

    /**
     * Reads all the file to see if the player has already played yet or not. If he has he stores
     * his wins and his score to sent to the other methods to change his wins and his score if he
     * achieved higher.
     * @param player the player to get his username.
     * @param typeOfGame Single player or Multiplayer.
     * @param nameOfTheFile Single player or Multiplayer.
     * @return a boolean to see if a player has already played or not.
     * @throws IOException if the file not found.
     */
    private boolean checkIfTheUserNameExists(Player player, int typeOfGame, String nameOfTheFile) throws IOException {
        File file = new File(nameOfTheFile);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] dataOfStr;
            if (typeOfGame == 1) {
                dataOfStr = data.split("[.:]", 3);
            } else {
                dataOfStr = data.split("[.:]", 4);
            }
            if (dataOfStr.length > 1) {
                dataOfStr[1] = dataOfStr[1].trim();
                if (dataOfStr[1].equals(player.getUsername())) {
                    scanner.close();
                    int position = Integer.parseInt(dataOfStr[0]);
                    if (typeOfGame == 1) {
                        double score = Double.parseDouble(dataOfStr[2]);
                        String originalStringOfTheFile = modifyTheStringToWrite(typeOfGame, position, player, 0, score);
                        if (player.getScore() > score) {
                            score = player.getScore();
                            replaceLines(originalStringOfTheFile, 0, player, position, score, "SinglePlayerFileScore.txt", typeOfGame);
                        }
                    } else {
                        dataOfStr[2] = dataOfStr[2].trim();
                        int wins = Integer.parseInt(dataOfStr[2]);
                        double score = Double.parseDouble(dataOfStr[3]);
                        String originalStringOfTheFile = modifyTheStringToWrite(typeOfGame, position, player, wins, score);
                        if (player.getScore() > score) {
                            score = player.getScore();
                        }
                        replaceLines(originalStringOfTheFile, wins, player, position, score, "MultiplayerFileScore.txt", typeOfGame);
                    }
                    return true;
                }
            }

        }
        return false;
    }


    /**
     * @param fileName Single player or Multiplayer.
     * @return a string that contains all the lines of a file.
     */
    public String readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }catch (IOException e){
        return " No scores made yet.";
        }
    }

}