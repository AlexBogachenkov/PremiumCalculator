import java.math.BigDecimal;

abstract class Coefficient {

    private final String riskType;
    private final BigDecimal baseRate;
    private final BigDecimal maxRate;
    private final BigDecimal maxRateAmount;

    Coefficient(String riskType, BigDecimal baseRate, BigDecimal maxRate, BigDecimal maxRateAmount) {
        this.riskType = riskType;
        this.baseRate = baseRate;
        this.maxRate = maxRate;
        this.maxRateAmount = maxRateAmount;
    }

    String getRiskType() {
        return riskType;
    }

    protected BigDecimal getBaseRate() {
        return baseRate;
    }

    protected BigDecimal getMaxRate() {
        return maxRate;
    }

    protected BigDecimal getMaxRateAmount() {
        return maxRateAmount;
    }

    abstract BigDecimal apply(BigDecimal sum);
}
