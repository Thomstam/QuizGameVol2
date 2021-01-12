package test;

import MainPackage.Gamemodes.HeatUp;
import MainPackage.Player;
import MainPackage.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for HeatUp. We use standard input from ExamplesImport class for a question and a
 * arrayList of players and we set for both of our players their placement. The first players is
 * simulated to answer correctly and the second wrong. So we check the result of players score to see
 * if the expected result is correct.
 */
public class HeatUpTest {

    @Test
    public void handleTheScore() {
        ExamplesImport examplesImport = new ExamplesImport();
        Question question = examplesImport.getQuestionToExample();
        ArrayList<Player> players = examplesImport.getPlayersToExample(question);
        HeatUp gamemode = new HeatUp();
        players.get(0).setPlacement("First");//Set second to see the problem
        players.get(1).setPlacement("Second");
        for (int i = 0; i < 5; i++) {
            gamemode.handleTheScore(players, question);
        }

        assertEquals(5000, players.get(0).getScore(), 0);
        assertEquals(0, players.get(1).getScore(), 0);
    }
}