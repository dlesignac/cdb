package fr.ebiz.cdb.service.validator;

import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Column;
import fr.ebiz.cdb.model.Order;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestPageValidator {

    private ComputerPageDTO computerPageDTO;

    @Before
    public void init() {
        computerPageDTO = new ComputerPageDTO("", Column.COMPUTER_NAME, Order.ASC, 10, 1);
    }

    @Test
    public void testFilterNull() {
        computerPageDTO.setFilter(null);
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testFilterEmpty() {
        computerPageDTO.setFilter("");
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(true));
    }

    @Test
    public void testSortNull() {
        computerPageDTO.setSort(null);
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testOrderNull() {
        computerPageDTO.setOrder(null);
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testLimitLessThanMin() {
        computerPageDTO.setLimit(0);
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testLimitMoreThanMax() {
        computerPageDTO.setLimit(101);
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testNumberLessThanMin() {
        computerPageDTO.setNumber(0);
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testInstantiate() {
        new PageValidator();
    }

}
