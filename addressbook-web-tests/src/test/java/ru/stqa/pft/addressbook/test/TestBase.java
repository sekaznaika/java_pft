package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager ap = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        ap.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        ap.stop();
    }

}
