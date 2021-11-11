package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase {

    @Test
    public void deleteContactTest() throws Exception {
        applicationManager.getContactHelper().selectContact();
        applicationManager.getContactHelper().clickDeleteContact();
        applicationManager.getContactHelper().closeDeleteAlert();
        applicationManager.getNavigationHelper().goToHome();
    }
}
