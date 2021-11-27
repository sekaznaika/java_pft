package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTest extends TestBase {


    @Test
    public void testCreateContact() throws Exception {
        List<ContactData> before = ap.contact().list();
        ContactData contact = new ContactData()
                .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                .withGroup("1");
        ap.contact().create(contact);
        List<ContactData> after = ap.contact().list();

        contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
