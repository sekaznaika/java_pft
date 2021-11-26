package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase {

    @Test
    public void deleteContactTest() throws Exception {
        ap.getNavigationHelper().goToHome();
        if (!ap.getContactHelper().isThereAContact()) {
            ap.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "Test5", "1"));
        }
        ap.getContactHelper().selectContact();
        ap.getContactHelper().clickDeleteContact();
        ap.getContactHelper().closeDeleteAlert();
        ap.getNavigationHelper().goToHome();
    }
}
