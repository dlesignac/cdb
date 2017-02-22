package fr.ebiz.cdb.service.validator;

import org.junit.Assert;
import org.junit.Test;

public class TestValidatorComputer {

    @Test
    public void testValidateNameNull() {
        Assert.assertEquals(ValidatorComputer.validateName(null), false);
    }

    @Test
    public void testValidateNameEmpty() {
        Assert.assertEquals(ValidatorComputer.validateName(""), false);
    }

    @Test
    public void testValidateName() {
        Assert.assertEquals(ValidatorComputer.validateName("name"), true);
    }

    @Test
    public void testValidateIntroducedNull() {
        Assert.assertEquals(ValidatorComputer.validateIntroduced(null), true);
    }

    @Test
    public void testValidateIntroducedEmpty() {
        Assert.assertEquals(ValidatorComputer.validateIntroduced(""), true);
    }

    @Test
    public void testValidateIntroducedInvalid() {
        Assert.assertEquals(ValidatorComputer.validateIntroduced("date"), false);
    }

    @Test
    public void testValidateIntroducedValid() {
        Assert.assertEquals(ValidatorComputer.validateIntroduced("2012-12-21"), true);
    }

    @Test
    public void testValidateDiscontinuedNull() {
        Assert.assertEquals(ValidatorComputer.validateDiscontinued(null), true);
    }

    @Test
    public void testValidateDiscontinuedEmpty() {
        Assert.assertEquals(ValidatorComputer.validateDiscontinued(""), true);
    }

    @Test
    public void testValidateDiscontinuedInvalid() {
        Assert.assertEquals(ValidatorComputer.validateDiscontinued("date"), false);
    }

    @Test
    public void testValidateDiscontinuedValid() {
        Assert.assertEquals(ValidatorComputer.validateDiscontinued("2012-12-21"), true);
    }

    @Test
    public void testValidateCompanyIdNull() {
        Assert.assertEquals(ValidatorComputer.validateCompanyId(null), true);
    }

    @Test
    public void testValidateCompanyIdEmpty() {
        Assert.assertEquals(ValidatorComputer.validateCompanyId(""), true);
    }

    @Test
    public void testValidateCompanyIdInvalid() {
        Assert.assertEquals(ValidatorComputer.validateDiscontinued("-1"), false);
    }

    @Test
    public void testValidateCompanyIdValid() {
        Assert.assertEquals(ValidatorComputer.validateDiscontinued("1"), false);
    }

}
