package test;

import MainPackage.Gamemodes.PointBuilder;
import MainPackage.Player;
import MainPackage.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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