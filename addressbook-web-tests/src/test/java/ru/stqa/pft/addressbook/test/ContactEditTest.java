package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().homePage();
        if (ap.contact().list().size()==0) {
            ap.contact().create(new ContactData("Test1", "Test2", "Test3", "test4", "1"));
        }
    }

    @Test
    public void testEditContact() throws Exception {

        List<ContactData> before = ap.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "zero", "zero", "zero", "zero", "null");
        ap.contact().modify(index, contact);
        List<ContactData> after = ap.contact().list();

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

