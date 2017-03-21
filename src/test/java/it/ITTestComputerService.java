package it;

import fr.ebiz.cdb.CDBRootConfig;
import fr.ebiz.cdb.core.model.Computer;
import fr.ebiz.cdb.core.service.ComputerService;
import fr.ebiz.cdb.core.service.IComputerService;
import fr.ebiz.cdb.core.service.exception.TransactionFailedException;
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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(CDBRootConfig.class);
        computerService = ctx.getBean(ComputerService.class);
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testCreate() throws TransactionFailedException {
        Computer computer = new Computer();
        computer.setName("Test computer");

        computerService.create(computer);
    }

}
