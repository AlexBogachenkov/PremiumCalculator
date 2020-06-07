import java.math.BigDecimal;
import java.util.List;

class PremiumCalculator {

    private static final BigDecimal DEFAULT_FIRE_COEFFICIENT = new BigDecimal("0.014");
    private static final BigDecimal OVERPRICED_FIRE_COEFFICIENT = new BigDecimal("0.024");
    private static final BigDecimal DEFAULT_THEFT_COEFFICIENT = new BigDecimal("0.11");
    private static final BigDecimal UNDERSTATED_THEFT_COEFFICIENT = new BigDecimal("0.05");

    public static void main(String[] args) {
        PremiumCalculator premiumCalculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        System.out.println(premiumCalculator.calculate(policy));
    }

    public BigDecimal calculate(Policy policy) {
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("100.00"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("8.00"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);

        BigDecimal premiumFire = applyCoefficientToSumInsuredFire(policy);
        BigDecimal premiumTheft = applyCoefficientToSumInsuredTheft(policy);
        BigDecimal premium = premiumFire.add(premiumTheft);
        return premium.setScale(2, BigDecimal.ROUND_HALF_UP);
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
        for (int i = 0; i < policyObjects.size(); i++) {
            PolicyObject tempObject = policyObjects.get(i);
            List<SubObject> subObjects = tempObject.getSubObjects();
            for (int j = 0; j < subObjects.size(); j++) {
                SubObject tempSubObject = subObjects.get(j);
                List<String> riskType = tempSubObject.getRiskType();
                for (int k = 0; k < riskType.size(); k++) {
                    String tempRiskType = riskType.get(k);
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
        for (int i = 0; i < policyObjects.size(); i++) {
            PolicyObject tempObject = policyObjects.get(i);
            List<SubObject> subObjects = tempObject.getSubObjects();
            for (int j = 0; j < subObjects.size(); j++) {
                SubObject tempSubObject = subObjects.get(j);
                List<String> riskType = tempSubObject.getRiskType();
                for (int k = 0; k < riskType.size(); k++) {
                    String tempRiskType = riskType.get(k);
                    if (tempRiskType.equals("THEFT")) {
                        sumInsured = sumInsured.add(tempSubObject.getSumInsured());
                    }
                }
            }
        }
        return sumInsured;
    }

    public boolean calculateSumInsuredFire(SubObject subObject) {
        List<String> riskType = subObject.getRiskType();
        int count = 0;
        for (String value : riskType) {
            if (value.equals("FIRE")) {
                count += 1;
            }
        }
        return count > 0;
    }

    public boolean calculateSumInsuredTheft(SubObject subObject) {
        List<String> riskType = subObject.getRiskType();
        int count = 0;
        for (String value : riskType) {
            if (value.equals("THEFT")) {
                count += 1;
            }
        }
        return count > 0;
    }
}
