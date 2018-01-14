package BootTest;

import server.Boot;
import common.model.game.Color;
import common.model.game.Game;
import common.model.game.Marble;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BootTest {
    Boot boot;

    Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game(2,17);
        boot = new Boot(game,Color.AZURE);
    }

    @Test
    public void testBoot(){
        Marble extremeMarble = game.getExtremePoint(boot.getColor().getOpposite());
        double dis1=0, dis2=0;
        boot.makeMove();

        for(int i=0; i<17; i++) {
            for (int j=0; j<17; j++) {
                System.out.print(boot.getGame().getBoard()[i][j].getColor() + " ");
            }
            System.out.println();
        }
    }

    @After
    public void tearDown() throws Exception {
    }
}
