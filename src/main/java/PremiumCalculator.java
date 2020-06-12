import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class PremiumCalculator {

    private static final BigDecimal DEFAULT_FIRE_COEFFICIENT = new BigDecimal("0.014");
    private static final BigDecimal OVERPRICED_FIRE_COEFFICIENT = new BigDecimal("0.024");
    private static final BigDecimal DEFAULT_THEFT_COEFFICIENT = new BigDecimal("0.11");
    private static final BigDecimal UNDERSTATED_THEFT_COEFFICIENT = new BigDecimal("0.05");

    public BigDecimal calculate(Policy policy) {
        BigDecimal premiumFire = applyCoefficientToSumInsuredFire(policy);
        BigDecimal premiumTheft = applyCoefficientToSumInsuredTheft(policy);
        BigDecimal premium = premiumFire.add(premiumTheft);
        return premium.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal applyCoefficientToSumInsuredFire(Policy policy) {
        BigDecimal sumInsuredFire = getSumInsuredFire(policy);
        int res = sumInsuredFire.compareTo(new BigDecimal("100.00"));
        if (res > 0) {
            return sumInsuredFire.multiply(OVERPRICED_FIRE_COEFFICIENT);
        } else {
            return sumInsuredFire.multiply(DEFAULT_FIRE_COEFFICIENT);
        }
    }

    public BigDecimal applyCoefficientToSumInsuredTheft(Policy policy) {
        BigDecimal sumInsuredTheft = getSumInsuredTheft(policy);
        int res = sumInsuredTheft.compareTo(new BigDecimal("15.00"));
        if (res >= 0) {
            return sumInsuredTheft.multiply(UNDERSTATED_THEFT_COEFFICIENT);
        } else {
            return sumInsuredTheft.multiply(DEFAULT_THEFT_COEFFICIENT);
        }
    }

    public BigDecimal getSumInsuredFire(Policy policy) {
        List<PolicyObject> policyObjects = policy.getPolicyObjects();
        BigDecimal sumInsured = BigDecimal.ZERO;
        for (PolicyObject tempObject : policyObjects) {
            List<SubObject> subObjects = tempObject.getSubObjects();
            for (SubObject tempSubObject : subObjects) {
                List<String> riskType = tempSubObject.getRiskType();
                for (String tempRiskType : riskType) {
                    if (tempRiskType.equals("FIRE")) {
                        sumInsured = sumInsured.add(tempSubObject.getSumInsured());
                    }
                }
            }
        }
        return sumInsured;
    }

    public BigDecimal getSumInsuredTheft(Policy policy) {
        List<PolicyObject> policyObjects = policy.getPolicyObjects();
        BigDecimal sumInsured = BigDecimal.ZERO;
        for (PolicyObject tempObject : policyObjects) {
            List<SubObject> subObjects = tempObject.getSubObjects();
            for (SubObject tempSubObject : subObjects) {
                List<String> riskType = tempSubObject.getRiskType();
                for (String tempRiskType : riskType) {
                    if (tempRiskType.equals("THEFT")) {
                        sumInsured = sumInsured.add(tempSubObject.getSumInsured());
                    }
                }
            }
        }
        return sumInsured;
    }
}
