package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @Test
    public void deleteContactTest() throws Exception {
        ap.getNavigationHelper().goToHome();
        if (!ap.getContactHelper().isThereAContact()) {
            ap.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "1"));
        }
        List<ContactData> before = ap.getContactHelper().getContactList();
        ap.getContactHelper().selectContact(before.size() - 1);
        ap.getContactHelper().clickDeleteContact();
        ap.getContactHelper().closeDeleteAlert();
        ap.getNavigationHelper().goToHome();
        List<ContactData> after = ap.getContactHelper().getContactList();

        before.remove(after.size() - 1);
        Assert.assertEquals(before, after);
    }
}
