package test;
import MainPackage.Gamemodes.Betting;
import MainPackage.Player;
import MainPackage.Question;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


/**
 * Test class for Betting. We use standard input from ExamplesImport class for a question and a
 * arrayList of players and we set the bet for both of our players to 250. The first players is
 * simulated to answer correctly and the second wrong. So we check the result of players score to see
 * if the expected result is correct.
 */
public class BettingTest {

    @Test
    public void handleTheScore() {
        ExamplesImport examplesImport = new ExamplesImport();
        Question question = examplesImport.getQuestionToExample();
        ArrayList<Player> players = examplesImport.getPlayersToExample(question);
        Betting gamemode = new Betting();
        players.get(0).setBet(250);
        players.get(1).setBet(250);
        gamemode.handleTheScore(players, question);
        assertEquals(250, players.get(0).getScore(), 0);
        assertEquals(-250, players.get(1).getScore(), 0);
    }
}