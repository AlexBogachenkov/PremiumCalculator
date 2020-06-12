import java.math.BigDecimal;

class FireCoefficient extends Coefficient {

    private static final String RISK_TYPE = "FIRE";
    private static final BigDecimal DEFAULT_RATE = new BigDecimal("0.014");
    private static final BigDecimal MAX_RATE = new BigDecimal("0.024");
    private static final BigDecimal MAX_RATE_AMOUNT = new BigDecimal("100.00");

    FireCoefficient() {
        super(RISK_TYPE, DEFAULT_RATE, MAX_RATE, MAX_RATE_AMOUNT);
    }

    BigDecimal apply(BigDecimal sum) {
        int res = sum.compareTo(getMaxRateAmount());
        BigDecimal rate = (res > 0) ? getMaxRate() : getBaseRate();
        return sum.multiply(rate);
    }
}
