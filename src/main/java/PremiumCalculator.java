import java.math.BigDecimal;
import java.util.List;

class PremiumCalculator {

    public static void main(String[] args) {
        PremiumCalculator premiumCalculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        System.out.println(premiumCalculator.calculate(policy));
    }

    public String calculate(Policy policy) {
        Object house = new Object("House");
        SubObject tv = new SubObject("TV", new BigDecimal("100.00"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("8.00"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);

        BigDecimal premiumFire = applyCoefficientToSumInsuredFire(policy);
        BigDecimal premiumTheft = applyCoefficientToSumInsuredTheft(policy);
        BigDecimal premium = premiumFire.add(premiumTheft);
        BigDecimal roundedPremium = premium.setScale(2,BigDecimal.ROUND_HALF_UP);
        return roundedPremium + " EUR";
    }

    public BigDecimal applyCoefficientToSumInsuredFire(Policy policy) {
        BigDecimal sumInsuredFire = getSumInsuredFire(policy);
        BigDecimal coefficientFireByDefault = new BigDecimal("0.014");
        BigDecimal coefficientFireForSumOverHundred = new BigDecimal("0.024");
        int res = sumInsuredFire.compareTo(new BigDecimal("100.00"));
        if (res > 0) {
            return sumInsuredFire.multiply(coefficientFireForSumOverHundred);
        } else {
            return sumInsuredFire.multiply(coefficientFireByDefault);
        }
    }

    public BigDecimal applyCoefficientToSumInsuredTheft(Policy policy) {
        BigDecimal sumInsuredTheft = getSumInsuredTheft(policy);
        BigDecimal coefficientTheftByDefault = new BigDecimal("0.11");
        BigDecimal coefficientTheftForSumEqualOrGreaterThanFifteen = new BigDecimal("0.05");
        int res = sumInsuredTheft.compareTo(new BigDecimal("15.00"));
        if ((res == 0) || (res > 0)) {
            return sumInsuredTheft.multiply(coefficientTheftForSumEqualOrGreaterThanFifteen);
        } else {
            return sumInsuredTheft.multiply(coefficientTheftByDefault);
        }
    }

    public BigDecimal getSumInsuredFire(Policy policy) {
        List<Object> policyObjects = policy.getPolicyObjects();
        BigDecimal sumInsured = new BigDecimal("00.00");
        for (int i = 0; i < policyObjects.size(); i++) {
            Object tempObject = policyObjects.get(i);
            List<SubObject> subObjects = tempObject.getSubObjects();
            for (int j = 0; j < subObjects.size(); j++){
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
        List<Object> policyObjects = policy.getPolicyObjects();
        BigDecimal sumInsured = new BigDecimal("00.00");
        for (int i = 0; i < policyObjects.size(); i++) {
            Object tempObject = policyObjects.get(i);
            List<SubObject> subObjects = tempObject.getSubObjects();
            for (int j = 0; j < subObjects.size(); j++){
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

    public boolean isFire(SubObject subObject) {
        List<String> riskType = subObject.getRiskType();
        int count = 0;
        for (String value : riskType) {
            if (value.equals("FIRE")) {
                count += 1;
            }
        }
        return count > 0;
    }

    public boolean isTheft(SubObject subObject) {
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
