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
    public void applyCoefficientToSumInsuredFireTest1() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("61.02"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("40.34"), "FIRE");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("2.43264"), calculator.applyCoefficientToSumInsuredFire(policy));
    }

    @Test
    public void applyCoefficientToSumInsuredFireTest2() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("61.02"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("15.64"), "FIRE");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("1.07324"), calculator.applyCoefficientToSumInsuredFire(policy));
    }

    @Test
    public void applyCoefficientToSumInsuredTheftTest1() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "THEFT");
        SubObject phone = new SubObject("Phone", new BigDecimal("1.23"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("1.0626"), calculator.applyCoefficientToSumInsuredTheft(policy));
    }

    @Test
    public void applyCoefficientToSumInsuredTheftTest2() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "THEFT");
        SubObject phone = new SubObject("Phone", new BigDecimal("6.57"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("0.7500"), calculator.applyCoefficientToSumInsuredTheft(policy));
    }

    @Test
    public void applyCoefficientToSumInsuredTheftTest3() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "THEFT");
        SubObject phone = new SubObject("Phone", new BigDecimal("34.23"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("2.1330"), calculator.applyCoefficientToSumInsuredTheft(policy));
    }

    @Test
    public void getSumInsuredFireTest1() {
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
        assertEquals(new BigDecimal("1040.90"), calculator.getSumInsuredFire(policy));
    }

    @Test
    public void getSumInsuredFireTest2() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        PolicyObject flat = new PolicyObject("Flat");
        PolicyObject hotel = new PolicyObject("Hotel");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "FIRE");
        SubObject phone = new SubObject("Phone", new BigDecimal("34.23"), "THEFT");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        SubObject fridge = new SubObject("Fridge", new BigDecimal("209.43"), "FIRE");
        SubObject pc = new SubObject("PC", new BigDecimal("823.04"), "FIRE");
        flat.addSubObjectToObject(fridge);
        flat.addSubObjectToObject(pc);
        SubObject conditioner = new SubObject("Conditioner", new BigDecimal("356.73"), "THEFT");
        SubObject dryer = new SubObject("Dryer", new BigDecimal("97.33"), "FIRE");
        hotel.addSubObjectToObject(conditioner);
        hotel.addSubObjectToObject(dryer);
        policy.addObjectToPolicy(flat);
        policy.addObjectToPolicy(house);
        policy.addObjectToPolicy(hotel);
        assertEquals(new BigDecimal("1138.23"), calculator.getSumInsuredFire(policy));
    }

    @Test
    public void getSumInsuredTheftTest1() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        PolicyObject flat = new PolicyObject("Flat");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "THEFT");
        SubObject phone = new SubObject("Phone", new BigDecimal("34.23"), "FIRE");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        SubObject fridge = new SubObject("Fridge", new BigDecimal("209.43"), "THEFT");
        SubObject pc = new SubObject("PC", new BigDecimal("823.04"), "THEFT");
        flat.addSubObjectToObject(fridge);
        flat.addSubObjectToObject(pc);
        policy.addObjectToPolicy(flat);
        policy.addObjectToPolicy(house);
        assertEquals(new BigDecimal("1040.90"), calculator.getSumInsuredTheft(policy));
    }

    @Test
    public void getSumInsuredTheftTest2() {
        PremiumCalculator calculator = new PremiumCalculator();
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED");
        PolicyObject house = new PolicyObject("House");
        PolicyObject flat = new PolicyObject("Flat");
        PolicyObject hotel = new PolicyObject("Hotel");
        SubObject tv = new SubObject("TV", new BigDecimal("8.43"), "THEFT");
        SubObject phone = new SubObject("Phone", new BigDecimal("34.23"), "FIRE");
        house.addSubObjectToObject(tv);
        house.addSubObjectToObject(phone);
        SubObject fridge = new SubObject("Fridge", new BigDecimal("209.43"), "THEFT");
        SubObject pc = new SubObject("PC", new BigDecimal("823.04"), "THEFT");
        flat.addSubObjectToObject(fridge);
        flat.addSubObjectToObject(pc);
        SubObject conditioner = new SubObject("Conditioner", new BigDecimal("356.73"), "FIRE");
        SubObject dryer = new SubObject("Dryer", new BigDecimal("97.33"), "THEFT");
        hotel.addSubObjectToObject(conditioner);
        hotel.addSubObjectToObject(dryer);
        policy.addObjectToPolicy(flat);
        policy.addObjectToPolicy(house);
        policy.addObjectToPolicy(hotel);
        assertEquals(new BigDecimal("1138.23"), calculator.getSumInsuredTheft(policy));
    }

}