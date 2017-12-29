package common.model.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTest {
    @Test
    public void testRandomColor() throws Exception {
        assertEquals(Color.AZURE, Color.randomColor());
    }

    @Test
    public void testGetOpposite() throws Exception {
        assertEquals(Color.AZURE, Color.BLUE.getOpposite());
        assertEquals(Color.BLUE, Color.BLUE.getOpposite().getOpposite());
        assertEquals(Color.PINK, Color.RED.getOpposite());
        assertEquals(Color.PINK, Color.PINK.getOpposite().getOpposite());
        assertEquals(Color.GREEN, Color.YELLOW.getOpposite());
        assertEquals(Color.GREEN, Color.GREEN.getOpposite().getOpposite());
        assertEquals(null, Color.NONE.getOpposite());
    }

}