package fr.ebiz.cdb.validator;

import fr.ebiz.cdb.dto.ComputerDTO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestComputerValidator {

    private ComputerDTO computerDTO;

    @Before
    public void init() {
        computerDTO = new ComputerDTO.Builder()
                .id(1)
                .name("test")
                .introduced("2012-12-12")
                .discontinued("2012-12-13")
                .companyId(1)
                .build();
    }

    @Test
    public void testValid() {
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testIdNull() {
        computerDTO.setId(null);
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testNameNull() {
        computerDTO.setName(null);
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testNameEmpty() {
        computerDTO.setName("");
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testNameTooLong() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            sb.append("*");
        }

        computerDTO.setName(sb.toString());
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testIntroducedNullDiscontinuedNull() {
        computerDTO.setIntroduced(null);
        computerDTO.setDiscontinued(null);
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testIntroducedEmptyDiscontinuedEmpty() {
        computerDTO.setIntroduced("");
        computerDTO.setDiscontinued("");
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testIntroducedNotDate() {
        computerDTO.setIntroduced("a");
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testDiscontinuedNotDate() {
        computerDTO.setDiscontinued("a");
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testIntroducedNotValidDate() {
        computerDTO.setIntroduced("2012-13-12");
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testDiscontinuedNotValidDate() {
        computerDTO.setDiscontinued("2012-13-12");
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testIntroducedAfterNow() {
        computerDTO.setIntroduced(LocalDate.now().plusDays(1).toString());
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testDiscontinuedBeforeIntroduced() {
        computerDTO.setDiscontinued("2012-12-11");
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testIntroducedNullDiscontinuedNotNull() {
        computerDTO.setIntroduced(null);
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testCompanyIdNull() {
        computerDTO.setCompanyId(null);
        boolean valid = ComputerValidator.validate(computerDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testInstantiate() {
        new ComputerValidator();
    }

}