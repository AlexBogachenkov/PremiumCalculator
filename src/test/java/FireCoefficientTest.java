import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FireCoefficientTest {

    private Coefficient coefficient;

    @Before
    public void setUp() {
        coefficient = new FireCoefficient();
    }

    @Test
    public void shouldApplyMaxRate() {
        assertEquals(new BigDecimal("3.45408"), coefficient.apply(new BigDecimal("143.92")));
    }

    @Test
    public void shouldApplyDefaultRate() {
        assertEquals(new BigDecimal("0.48328"), coefficient.apply(new BigDecimal("34.52")));
    }
}