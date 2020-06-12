import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

class PremiumCalculator {

    public BigDecimal calculate(Policy policy) {
        BigDecimal totalPremium = BigDecimal.ZERO;
        for (Coefficient coefficient : getCoefficients()) {
            BigDecimal premium = applyCoefficientToSumInsured(policy, coefficient);
            totalPremium = totalPremium.add(premium);
        }

        return totalPremium.setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal applyCoefficientToSumInsured(Policy policy, Coefficient coefficient) {
        BigDecimal sumInsured = getSumInsured(policy, coefficient.getRiskType());
        return coefficient.apply(sumInsured);
    }

    BigDecimal getSumInsured(Policy policy, String riskType) {
        List<PolicyObject> policyObjects = policy.getPolicyObjects();
        BigDecimal sumInsured = BigDecimal.ZERO;
        for (PolicyObject tempObject : policyObjects) {
            List<SubObject> subObjects = tempObject.getSubObjects();
            for (SubObject tempSubObject : subObjects) {
                List<String> type = tempSubObject.getRiskType();
                for (String tempRiskType : type) {
                    if (tempRiskType.equals(riskType)) {
                        sumInsured = sumInsured.add(tempSubObject.getSumInsured());
                    }
                }
            }
        }
        return sumInsured;
    }

    private List<Coefficient> getCoefficients() {
        return Arrays.asList(new TheftCoefficient(), new FireCoefficient());
    }
}
