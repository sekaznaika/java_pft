package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.getNavigationHelper().goToHome();
        if (!ap.getContactHelper().isThereAContact()) {
            ap.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "1"));
        }
    }

    @Test
    public void deleteContactTest() throws Exception {
        List<ContactData> before = ap.getContactHelper().getContactList();
        ap.getContactHelper().selectContact(before.size() - 1);
        ap.getContactHelper().clickDeleteContact();
        ap.getContactHelper().closeDeleteAlert();
        ap.getNavigationHelper().goToHome();
        List<ContactData> after = ap.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(after.size() - 1);
        Assert.assertEquals(before, after);
    }
}
