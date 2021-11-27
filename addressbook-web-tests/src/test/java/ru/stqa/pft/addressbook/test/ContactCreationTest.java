package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTest extends TestBase {


    @Test
    public void testCreateContact() throws Exception {
        Set<ContactData> before = ap.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                .withGroup("1");
        ap.contact().create(contact);
        Set<ContactData> after = ap.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }


}
