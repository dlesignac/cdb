package fr.ebiz.cdb.it;

import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ITDashboard {
    private static final String SYSTEM_GECKO = "webdriver.gecko.driver";

    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String PROPERTY_BASE_URL = "test.baseUrl";
    private static final String PROPERTY_GECKO_DRIVER = "test.gecko.driver";
    private static final String PROPERTY_SQL_SCHEMA = "sql/schema.sql";
    private static final String PROPERTY_SQL_DATA = "sql/data.sql";

    private static WebDriver driver;
    private static String baseUrl;

    @Before
    public void setUp() throws IOException, DatasourceException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(APPLICATION_PROPERTIES);

        Properties properties = new Properties();
        properties.load(propertiesFile);

        System.setProperty(SYSTEM_GECKO, properties.getProperty(PROPERTY_GECKO_DRIVER));

        ScriptRunner sr = new ScriptRunner(ConnectionManager.INSTANCE.getConnection());

        File schema = new File(classLoader.getResource(PROPERTY_SQL_SCHEMA).getFile());
        Reader reader = new BufferedReader(new FileReader(schema));
        sr.runScript(reader);
        reader.close();

        File data = new File(classLoader.getResource(PROPERTY_SQL_DATA).getFile());
        reader = new BufferedReader(new FileReader(data));
        sr.runScript(reader);
        reader.close();

        baseUrl = properties.getProperty(PROPERTY_BASE_URL);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testGet() throws Exception {
        driver.get(baseUrl + "/computer-database/dashboard");
        assert (isElementPresent(By.linkText("ACE")));
        assert (isElementPresent(By.linkText("1")));
        assert (isElementPresent(By.linkText("5")));
        assert (!isElementPresent(By.linkText("«")));
    }

    @Test
    public void testPagination() throws Exception {
        driver.get(baseUrl + "/computer-database/dashboard");
        assert (!isElementPresent(By.linkText("«")));

        driver.get(baseUrl + "/computer-database/dashboard?page=56");
        assert (!isElementPresent(By.linkText("»")));
    }

    @Test
    public void testAddComputer() throws Exception {
        driver.get(baseUrl + "/computer-database/add-computer");
        driver.findElement(By.id(("computerNameContainer"))).sendKeys("test");
        driver.findElement(By.id(("submit"))).click();
        driver.get(baseUrl + "/computer-database/dashboard?page=49");
        assert (!isElementPresent(By.linkText("test")));
    }

    private boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

}
