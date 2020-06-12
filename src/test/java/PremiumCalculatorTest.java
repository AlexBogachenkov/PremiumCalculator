import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class PremiumCalculatorTest {

    @Test
    public void firstAcceptanceCriteria() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("100.00"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("8.00"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("2.28"), calculator.calculate(policy));
    }

    @Test
    public void secondAcceptanceCriteria() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("500.00"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("102.51"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("17.13"), calculator.calculate(policy));
    }

    @Test
    public void shouldApplyCoefficient() {
        PremiumCalculator calculator = new PremiumCalculator();
        FireCoefficient fireCoefficient = new FireCoefficient();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("61.02"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("40.34"), "FIRE");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("2.43264"), calculator.applyCoefficientToSumInsured(policy, fireCoefficient));
    }

    @Test
    public void shouldNotApplyCoefficient() {
        PremiumCalculator calculator = new PremiumCalculator();
        FireCoefficient fireCoefficient = new FireCoefficient();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("61.02"), "THEFT");
        SubObject phone = new SubObject("Phone", new BigDecimal("15.64"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("0.000"), calculator.applyCoefficientToSumInsured(policy, fireCoefficient));
    }

    @Test
    public void shouldReturnSum() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        PolicyObject flat = new PolicyObject("Flat");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("34.23"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        SubObject fridge = new SubObject("Fridge", new BigDecimal("209.43"), "FIRE");
        SubObject pc = new SubObject("PC", new BigDecimal("823.04"), "FIRE");
        flat.addSubObjectToObject(fridge);
        flat.addSubObjectToObject(pc);
        policy.addObjectToPolicy(flat);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("1040.90"), calculator.getSumInsured(policy, "FIRE"));
    }

    @Test
    public void shouldNotReturnSum() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        PolicyObject flat = new PolicyObject("Flat");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("34.23"), "FIRE");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        SubObject fridge = new SubObject("Fridge", new BigDecimal("209.43"), "FIRE");
        SubObject pc = new SubObject("PC", new BigDecimal("823.04"), "FIRE");
        flat.addSubObjectToObject(fridge);
        flat.addSubObjectToObject(pc);
        policy.addObjectToPolicy(flat);
        policy.addObjectToPolicy(house);
        assertEquals(BigDecimal.ZERO, calculator.getSumInsured(policy, "THEFT"));
    }

}