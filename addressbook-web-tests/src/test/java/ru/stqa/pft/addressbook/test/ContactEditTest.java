package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactEditTest extends TestBase {

    @Test
    public void testEditContact() throws Exception {
        applicationManager.getNavigationHelper().goToHome();
        if (!applicationManager.getContactHelper().isThereAContact()) {
            applicationManager.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "Test5", "1"));
        }
        applicationManager.getContactHelper().clickEditContact();
        applicationManager.getContactHelper().fillTheForm(new ContactData("zero", "zero", "zero", "zero", "zero", null), false);
        applicationManager.getContactHelper().updateChanges();
        applicationManager.getContactHelper().returnHomePage();


    }
}
