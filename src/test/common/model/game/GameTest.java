package common.model.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    @Before
    public void setUp() throws Exception {
        game = new Game(2,17);
    }

    @Test
    public void testGame(){
        assertNull(game.getBoard()[0][6]);
        assertEquals(Color.BLUE,game.getBoard()[16][12].getTerritory());

        assertEquals(Color.BLUE,game.getBoard()[13][9].getColor());
        assertEquals(Color.NONE,game.getBoard()[12][9].getColor());
        if(game.canMove(13,9,12,9, Color.BLUE)) {
            game.makeMove(13, 9 , 12 , 9, Color.BLUE);
        }
        assertEquals(Color.NONE,game.getBoard()[13][9].getColor());
        assertEquals(Color.BLUE,game.getBoard()[12][9].getColor());

        assertEquals(Color.BLUE,game.getBoard()[14][10].getColor());
        assertEquals(Color.NONE,game.getBoard()[12][10].getColor());
        if(game.canJump(14,10,12,10, Color.BLUE)) {
            game.makeMove(14, 10 , 12 , 10, Color.BLUE);
        }
        assertEquals(Color.NONE,game.getBoard()[14][10].getColor());
        assertEquals(Color.BLUE,game.getBoard()[12][10].getColor());

    }

    @After
    public void tearDown() throws Exception {
    }

}