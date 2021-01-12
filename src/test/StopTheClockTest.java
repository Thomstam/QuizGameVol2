package test;

import MainPackage.Gamemodes.StopTheClock;
import MainPackage.Player;
import MainPackage.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for StopTheClock. We use standard input from ExamplesImport class for a question and a
 * arrayList of players and we set for both of our players their time. The first players is
 * simulated to answer correctly and the second wrong. So we check the result of players score to see
 * if the expected result is correct.
 */
public class StopTheClockTest {

    @Test
    public void handleTheScore() {
        ExamplesImport examplesImport = new ExamplesImport();
        Question question = examplesImport.getQuestionToExample();
        ArrayList<Player> players = examplesImport.getPlayersToExample(question);
        StopTheClock stopTheClock = new StopTheClock();
        players.get(0).setTimeLeftFromAnswer(2556);
        players.get(1).setTimeLeftFromAnswer(3587);
        stopTheClock.handleTheScore(players, question);
        assertEquals(2556 * 0.2, players.get(0).getScore(), 0);
        assertEquals(0, players.get(1).getScore(), 0);
    }
}