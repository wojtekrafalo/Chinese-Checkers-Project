package BootTest;

import common.model.game.Boot;
import common.model.game.Color;
import common.model.game.Game;
import common.model.game.Marble;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Marble extremeMarble = game.getExtremePoint(boot.getColor().getOpposite());
        double dis1=0, dis2=0;
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
//        for (int i=0;i<17;i++) {
//            for (int j=0;j<17;j++) {
//                if (boot.getGame().getBoard()[i][j].getColor() == boot.getColor()) {
//                    dis1+=distance(i,j, extremeMarble.getX(), extremeMarble.getY());
//                }
//            }
//        }
//        boot.makeMove();
//        for (int i=0;i<17;i++) {
//            for (int j=0;j<17;j++) {
//                if (boot.getGame().getBoard()[i][j].getColor() == boot.getColor()) {
//                    dis2+=distance(i,j, extremeMarble.getX(), extremeMarble.getY());
//                }
//            }
//        }
//        assertTrue("", dis1>dis2);
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
