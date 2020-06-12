import java.math.BigDecimal;

class TheftCoefficient extends Coefficient {

    private static final String RISK_TYPE = "THEFT";
    private static final BigDecimal DEFAULT_RATE = new BigDecimal("0.11");
    private static final BigDecimal MAX_RATE = new BigDecimal("0.05");
    private static final BigDecimal MAX_RATE_AMOUNT = new BigDecimal("15.00");

    TheftCoefficient() {
        super(RISK_TYPE, DEFAULT_RATE, MAX_RATE, MAX_RATE_AMOUNT);
    }

    BigDecimal apply(BigDecimal sum) {
        int res = sum.compareTo(getMaxRateAmount());
        BigDecimal rate = (res >= 0) ? getMaxRate() : getBaseRate();
        return sum.multiply(rate);
    }
}
