package fr.ebiz.cdb.service.validator;

import fr.ebiz.cdb.model.Computer;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestValidatorComputer {

    @Test
    public void testInstantiate() {
        new ComputerValidator();
    }

    @Test
    public void testValidateIdNegative() {
        Assert.assertEquals(ComputerValidator.validateId("-1"), false);
    }

    @Test
    public void testValidateIdNull() {
        Assert.assertEquals(ComputerValidator.validateId(null), true);
    }

    @Test
    public void testValidateIdValid() {
        Assert.assertEquals(ComputerValidator.validateId("0"), true);
    }

    @Test
    public void testValidateNameNull() {
        Assert.assertEquals(ComputerValidator.validateName(null), false);
    }

    @Test
    public void testValidateNameEmpty() {
        Assert.assertEquals(ComputerValidator.validateName(""), false);
    }

    @Test
    public void testValidateName() {
        Assert.assertEquals(ComputerValidator.validateName("name"), true);
    }

    @Test
    public void testValidateIntroducedNull() {
        Assert.assertEquals(ComputerValidator.validateIntroduced(null), true);
    }

    @Test
    public void testValidateIntroducedEmpty() {
        Assert.assertEquals(ComputerValidator.validateIntroduced(""), true);
    }

    @Test
    public void testValidateIntroducedInvalid() {
        Assert.assertEquals(ComputerValidator.validateIntroduced("date"), false);
    }

    @Test
    public void testValidateIntroducedAlmostValid() {
        Assert.assertEquals(ComputerValidator.validateIntroduced("2012-13-12"), false);
    }

    @Test
    public void testValidateIntroducedValid() {
        Assert.assertEquals(ComputerValidator.validateIntroduced("2012-12-21"), true);
    }

    @Test
    public void testValidateDiscontinuedNull() {
        Assert.assertEquals(ComputerValidator.validateDiscontinued(null), true);
    }

    @Test
    public void testValidateDiscontinuedEmpty() {
        Assert.assertEquals(ComputerValidator.validateDiscontinued(""), true);
    }

    @Test
    public void testValidateDiscontinuedInvalid() {
        Assert.assertEquals(ComputerValidator.validateDiscontinued("date"), false);
    }

    @Test
    public void testValidateDiscontinuedValid() {
        Assert.assertEquals(ComputerValidator.validateDiscontinued("2012-12-21"), true);
    }

    @Test
    public void testValidateCompanyIdNull() {
        Assert.assertEquals(ComputerValidator.validateCompanyId(null), true);
    }

    @Test
    public void testValidateCompanyIdEmpty() {
        Assert.assertEquals(ComputerValidator.validateCompanyId(""), true);
    }

    @Test
    public void testValidateCompanyIdInvalid() {
        Assert.assertEquals(ComputerValidator.validateDiscontinued("-1"), false);
    }

    @Test
    public void testValidateCompanyIdValid() {
        Assert.assertEquals(ComputerValidator.validateDiscontinued("1"), false);
    }

    @Test
    public void testValidateComputerNoName() {
        Assert.assertEquals(ComputerValidator.validate(new Computer()), false);
    }

    @Test
    public void testValidateComputerValid() {
        Computer computer = new Computer();
        computer.setName("test");
        Assert.assertEquals(ComputerValidator.validate(computer), true);
    }

    @Test
    public void testValidateIntroNullDiscoNotNull() {
        Computer computer = new Computer();
        computer.setName("test");
        computer.setDiscontinued(LocalDate.parse("2012-12-12"));
        Assert.assertEquals(ComputerValidator.validate(computer), false);
    }

    @Test
    public void testValidateDiscoAfterIntro() {
        Computer computer = new Computer();
        computer.setName("test");
        computer.setIntroduced(LocalDate.parse("2012-12-13"));
        computer.setDiscontinued(LocalDate.parse("2012-12-12"));
        Assert.assertEquals(ComputerValidator.validate(computer), false);
    }

}
