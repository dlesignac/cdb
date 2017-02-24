package fr.ebiz.cdb.service.datasource;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPage {

    private List<Integer> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add(0);
    }

    @Test(expected = NullPointerException.class)
    public void testNewEntriesNull() {
        new Page<>(1, 1, 1, null);
    }

    @Test(expected = RuntimeException.class)
    public void testNewLimitZero() {
        new Page<>(0, 1, 1, list);
    }

    @Test(expected = RuntimeException.class)
    public void testNewCountNegative() {
        new Page<>(1, -1, 1, list);
    }

    @Test(expected = RuntimeException.class)
    public void testNewNumberZero() {
        new Page<>(1, 1, 0, list);
    }

    @Test
    public void testNew() {
        new Page<>(1, 0, 1, new ArrayList<>());
    }

}
