package common.model.game;

import org.testng.annotations.Test;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest {
    @Test
    public void randomColor() throws Exception {
        assertEquals(Color.AZURE, Color.randomColor());
    }

    @Test
    public void getOpposite() throws Exception {
        assertEquals(Color.AZURE, Color.BLUE.getOpposite());
        assertEquals(Color.BLUE, Color.BLUE.getOpposite().getOpposite());
        assertEquals(Color.PINK, Color.RED.getOpposite());
        assertEquals(Color.PINK, Color.PINK.getOpposite().getOpposite());
        assertEquals(Color.GREEN, Color.YELLOW.getOpposite());
        assertEquals(Color.GREEN, Color.GREEN.getOpposite().getOpposite());
        assertEquals(null, Color.NONE.getOpposite());
    }

}