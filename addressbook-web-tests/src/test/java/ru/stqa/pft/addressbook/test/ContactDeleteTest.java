package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase {

    @Test
    public void deleteContactTest() throws Exception {
        applicationManager.getNavigationHelper().goToHome();
        if (!applicationManager.getContactHelper().isThereAContact()) {
            applicationManager.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "Test5", "1"));
        }
        applicationManager.getContactHelper().selectContact();
        applicationManager.getContactHelper().clickDeleteContact();
        applicationManager.getContactHelper().closeDeleteAlert();
        applicationManager.getNavigationHelper().goToHome();
    }
}
