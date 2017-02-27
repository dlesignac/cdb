package fr.ebiz.cdb.service.validator;

import org.junit.Assert;
import org.junit.Test;

public class TestValidatorComputer {

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

}
