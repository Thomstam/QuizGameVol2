package test;

import MainPackage.Gamemodes.PointBuilder;
import MainPackage.Player;
import MainPackage.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for Pointbuilder. We use standard input from ExamplesImport class for a question and a
 * arrayList of player. The first players is simulated to answer correctly and the second wrong.
 * So we check the result of players score to see if the expected result is correct.
 */
public class PointBuilderTest {

    @Test
    public void handleTheScore() {
        ExamplesImport examplesImport = new ExamplesImport();
        Question question = examplesImport.getQuestionToExample();
        ArrayList<Player> players = examplesImport.getPlayersToExample(question);
        PointBuilder pointBuilder = new PointBuilder();
        pointBuilder.handleTheScore(players, question);
        assertEquals(1000, players.get(0).getScore(), 0);
        assertEquals(0, players.get(1).getScore(), 0);
    }
}