package BootTest;

import common.model.game.Boot;
import common.model.game.Color;
import common.model.game.Game;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BootTest {
    Game game;
    Boot boot;

    @Before
    public void setUp() throws Exception {
        game = new Game(2,17);
        boot = new Boot(game,Color.AZURE);
    }

    @Test
    public void testBoot(){
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
        TestCase.assertTrue(boot.makeMove());
//        assertEquals(Color.BLUE,game.getBoard()[13][9].getColor());
//        assertEquals(Color.NONE,game.getBoard()[12][9].getColor());
//        game.makeMove(13,9,12,9, Color.BLUE);
//        assertEquals(Color.NONE,game.getBoard()[13][9].getColor());
//        assertEquals(Color.BLUE,game.getBoard()[12][9].getColor());
    }

    @After
    public void tearDown() throws Exception {
    }
}
