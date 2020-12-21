package MainPackage;
import java.io.*;
import java.util.Scanner;


public class ScoreSaving {

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
                if (checkIfTheUserNameExists(player, 1, "SinglePlayerFileScore.txt")){

                }else{
                    FileWriter fileWriter = new FileWriter("SinglePlayerFileScore.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String stringToBeWritten = formattedStringToWrite(player, 1, 0,  countLinesOld("SinglePlayerFileScore.txt"));
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
                    String stringToBeWritten = formattedStringToWrite(player, 1, 0,  1);
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
                if (checkIfTheUserNameExists(player, 2, "MultiplayerFileScore.txt")){

                }else{
                    FileWriter fileWriter = new FileWriter("MultiplayerFileScore.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("\n");
                    String stringToBeWritten = formattedStringToWrite(player, 2, 1,  countLinesOld("MultiplayerFileScore.txt"));
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
                    String stringToBeWritten = formattedStringToWrite(player, 2, 1,  1);
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

    private String formattedStringToWrite(Player player, int typeOfTheGame, int  wins, int position){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(position).append(".").append(player.getUsername());
        stringBuilder.append(" ".repeat(Math.max(0, 44 - player.getUsername().length())));
        if (typeOfTheGame != 1) {
            stringBuilder.append(":").append(wins);
            stringBuilder.append(" ".repeat(Math.max(0, 32)));
        }
        stringBuilder.append(":").append(player.getScore());
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

    private void replaceLines(String lineToChance, int wins, Player player, int position, double score, String nameOfTheFile, int typeOfTheGame) {
        try {

            BufferedReader file = new BufferedReader(new FileReader(nameOfTheFile));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            int lineOfTheFile = countLinesOld(nameOfTheFile);
            int counter = 0;
            String theLineOfTheFileAfterModification;
            theLineOfTheFileAfterModification = modifyTheStringToWrite(typeOfTheGame, position, player, wins + 1, score );
            while ((line = file.readLine()) != null && counter < lineOfTheFile + 1) {
                if (line.equals(lineToChance)){
                    line = theLineOfTheFileAfterModification;
                }
                inputBuffer.append(line);
                if (counter != lineOfTheFile){
                    inputBuffer.append('\n');
                }
                counter++;
            }
            file.close();


            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(nameOfTheFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    private String modifyTheStringToWrite(int typeOfTheGame, int position, Player player, int wins, double score ){
        if (typeOfTheGame == 1){
            return position + "." + player.getUsername() +
                    " ".repeat(Math.max(0, 44 - player.getUsername().length())) +
                    ":" + score;
        }else {
            return position + "." + player.getUsername() +
                    " ".repeat(Math.max(0, 44 - player.getUsername().length())) +
                    ":" + wins + " ".repeat(Math.max(0, 33 - String.valueOf(wins).length())) + ":" + score;
        }
    }

    private boolean checkIfTheUserNameExists(Player player, int typeOfGame, String nameOfTheFile) throws IOException {
        File file = new File(nameOfTheFile);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            String[] dataOfStr;
            if (typeOfGame == 1){
                dataOfStr = data.split("[.:]", 3);
            }else {
                dataOfStr = data.split("[.:]", 4);
            }
            if (dataOfStr.length > 1){
                dataOfStr[1] = dataOfStr[1].trim();
                if (dataOfStr[1].equals(player.getUsername())){
                    scanner.close();
                    int position = Integer.parseInt(dataOfStr[0]);
                    if (typeOfGame == 1){
                        double score = Double.parseDouble(dataOfStr[2]);
                        String originalStringOfTheFile = modifyTheStringToWrite(typeOfGame, position, player, 0, score);
                        if (player.getScore() > score){
                            score = player.getScore();
                            replaceLines(originalStringOfTheFile, 0, player, position, score, "SinglePlayerFileScore.txt", typeOfGame);
                        }
                    }else {
                        dataOfStr[2] = dataOfStr[2].trim();
                        int wins = Integer.parseInt(dataOfStr[2]);
                        double score = Double.parseDouble(dataOfStr[3]);
                        String originalStringOfTheFile = modifyTheStringToWrite(typeOfGame, position, player, wins, score);
                        if (player.getScore() > score){
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

    public String readFile(String fileName) throws IOException {
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
