package fr.ebiz.cdb.service;

import fr.ebiz.cdb.AppConfig;
import fr.ebiz.cdb.core.Computer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ITTestComputerService {
    private static IComputerService computerService;

    @BeforeClass
    public static void init() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        computerService = ctx.getBean(ComputerService.class);
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testCreate() {
        Computer computer = new Computer();
        computer.setName("Test computer");
        computerService.create(computer);
    }

}
