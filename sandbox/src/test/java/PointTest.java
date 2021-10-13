import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PointTest {

    @Test
    public void testDistance() {
        Point tp = new Point(1.0,3.0);
        Point tp2 = new Point(3.0,5.0);
        Assert.assertEquals(tp.distance(tp2), 2.8284271247461903);
    }

    @Test
    public void testPointsEqual() {
        Point tp = new Point(1.0,1.0);
        Point tp2 = new Point(1.0,1.0);
        Assert.assertEquals(tp.distance(tp2),0);
    }

    @Test
    public void testNullPonts() {
        Point tp = new Point(0.0,0.0);
        Point tp2 = new Point(0.0,0.0);
        Assert.assertEquals(tp.distance(tp2),0);
    }
}