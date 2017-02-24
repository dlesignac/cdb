package fr.ebiz.cdb.mapper;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.validator.ValidatorComputer;
import fr.ebiz.cdb.service.validator.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;

import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidatorComputer.class)
public class TestMapperComputer {

    @Before
    public void setUp() {
        PowerMockito.mockStatic(ValidatorComputer.class);
        Mockito.when(ValidatorComputer.validateName("true")).thenReturn(true);
        Mockito.when(ValidatorComputer.validateName("false")).thenReturn(false);
        Mockito.when(ValidatorComputer.validateIntroduced("2012-12-21")).thenReturn(true);
        Mockito.when(ValidatorComputer.validateIntroduced("false")).thenReturn(false);
        Mockito.when(ValidatorComputer.validateDiscontinued("2012-12-22")).thenReturn(true);
        Mockito.when(ValidatorComputer.validateDiscontinued("false")).thenReturn(false);
        Mockito.when(ValidatorComputer.validateCompanyId("1")).thenReturn(true);
        Mockito.when(ValidatorComputer.validateCompanyId("false")).thenReturn(false);
        Mockito.when(ValidatorComputer.validate(any())).thenReturn(true);
    }

    @Test(expected = ValidationException.class)
    public void testMapNameIllegal() throws ValidationException {
        MapperComputer.map("false", "2012-12-21", "2012-12-22", "1");
    }

    @Test(expected = ValidationException.class)
    public void testMapIntroducedIllegal() throws ValidationException {
        MapperComputer.map("true", "false", "2012-12-22", "1");
    }

    @Test(expected = ValidationException.class)
    public void testMapDiscontinuedIllegal() throws ValidationException {
        MapperComputer.map("true", "2012-12-21", "false", "1");
    }

    @Test(expected = ValidationException.class)
    public void testMapCompanyIdIllegal() throws ValidationException {
        MapperComputer.map("true", "2012-12-21", "2012-12-22", "false");
    }

    @Test
    public void testMap() throws ValidationException {
        Computer computer =
                MapperComputer.map("true", "2012-12-21", "2012-12-22", "1");
        Assert.assertEquals(computer.getName(), "true");
        Assert.assertEquals(computer.getIntroduced(), LocalDate.parse("2012-12-21"));
        Assert.assertEquals(computer.getDiscontinued(), LocalDate.parse("2012-12-22"));
        Assert.assertEquals(computer.getManufacturer().getId(), 1);
    }

}
