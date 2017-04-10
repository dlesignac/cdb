package fr.ebiz.cdb.validator;

import fr.ebiz.cdb.binding.PageRequest;
import fr.ebiz.cdb.core.Sort;
import fr.ebiz.cdb.core.Order;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestPageValidator {
    private PageRequest computerPageDTO;

    @Before
    public void init() {
        computerPageDTO = new PageRequest();
        computerPageDTO.setFilter("");
        computerPageDTO.setSort(Sort.COMPUTER_NAME);
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
