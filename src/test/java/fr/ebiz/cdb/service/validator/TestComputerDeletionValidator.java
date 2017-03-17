package fr.ebiz.cdb.service.validator;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TestComputerDeletionValidator {

    private ComputerDeletionDTO computerDeletionDTO;

    @Before
    public void init() {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        computerDeletionDTO = new ComputerDeletionDTO(ids);
    }

    @Test
    public void testValid() {
        boolean valid = ComputerDeletionValidator.validate(computerDeletionDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testIdNotInt() {
        computerDeletionDTO.getIds().add("a");
        boolean valid = ComputerDeletionValidator.validate(computerDeletionDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testInstantiate() {
        new ComputerDeletionValidator();
    }

}
