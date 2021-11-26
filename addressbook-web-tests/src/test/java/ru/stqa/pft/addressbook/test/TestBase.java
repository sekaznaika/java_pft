package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager ap = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        ap.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        ap.stop();
    }

}
