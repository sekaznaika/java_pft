package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactEditTest extends TestBase {

    @Test
    public void testEditContact() throws Exception {
        ap.getNavigationHelper().goToHome();
        if (!ap.getContactHelper().isThereAContact()) {
            ap.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "1"));
        }
        List<ContactData> before = ap.getContactHelper().getContactList();
        ap.getContactHelper().clickEditContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "zero", "zero", "zero", "zero", "null");
        ap.getContactHelper().fillTheForm(contact, false);
        ap.getContactHelper().updateChanges();
        ap.getContactHelper().returnHomePage();
        List<ContactData> after = ap.getContactHelper().getContactList();

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }
}
