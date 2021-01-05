package test;

import MainPackage.Gamemodes.Betting;
import MainPackage.Player;
import MainPackage.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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