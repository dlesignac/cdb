package fr.ebiz.cdb.core.validator;

import fr.ebiz.cdb.core.dto.ComputerPageRequest;
import fr.ebiz.cdb.core.model.Column;
import fr.ebiz.cdb.core.model.Order;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestPageValidator {

    private ComputerPageRequest computerPageDTO;

    @Before
    public void init() {
        computerPageDTO = new ComputerPageRequest();
        computerPageDTO.setFilter("");
        computerPageDTO.setSort(Column.COMPUTER_NAME);
        computerPageDTO.setOrder(Order.ASC);
        computerPageDTO.setLimit(10);
        computerPageDTO.setPage(1);
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
        computerPageDTO.setPage(0);
        boolean valid = PageValidator.validate(computerPageDTO).isEmpty();
        assertThat(valid, is(false));
    }

    @Test
    public void testInstantiate() {
        new PageValidator();
    }

}