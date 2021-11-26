package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactEditTest extends TestBase {

    @Test
    public void testEditContact() throws Exception {
        ap.getNavigationHelper().goToHome();
        if (!ap.getContactHelper().isThereAContact()) {
            ap.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "Test5", "1"));
        }
        ap.getContactHelper().clickEditContact();
        ap.getContactHelper().fillTheForm(new ContactData("zero", "zero", "zero", "zero", "zero", null), false);
        ap.getContactHelper().updateChanges();
        ap.getContactHelper().returnHomePage();


    }
}
