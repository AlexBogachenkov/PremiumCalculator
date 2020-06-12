import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TheftCoefficientTest {

    private Coefficient coefficient;

    @Before
    public void setUp() {
        coefficient = new TheftCoefficient();
    }

    @Test
    public void shouldApplyMaxRate() {
        assertEquals(new BigDecimal("1.0170"),coefficient.apply(new BigDecimal("20.34")));
    }

    @Test
    public void shouldApplyDefaultRate() {
        assertEquals(new BigDecimal("1.0274"),coefficient.apply(new BigDecimal("9.34")));
    }

    @Test
    public void shouldApplyMaxRate_whenAmountEqualsToMaxRateAmount() {
        assertEquals(new BigDecimal("0.7500"),coefficient.apply(new BigDecimal("15.00")));
    }
}