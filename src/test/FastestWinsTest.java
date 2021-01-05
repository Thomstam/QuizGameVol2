package test;

import MainPackage.Gamemodes.FastestWins;
import MainPackage.Player;
import MainPackage.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FastestWinsTest {

    @Test
    public void handleTheScore() {
        ExamplesImport examplesImport = new ExamplesImport();
        Question question = examplesImport.getQuestionToExample();
        ArrayList<Player> players = examplesImport.getPlayersToExample(question);
        FastestWins gamemode = new FastestWins();
        players.get(0).setPlacement("First");//Set second to see the problem
        players.get(1).setPlacement("Second");
        gamemode.handleTheScore(players, question);
        assertEquals(1000, players.get(0).getScore(), 0);
        assertEquals(0, players.get(1).getScore(), 0);
    }
}