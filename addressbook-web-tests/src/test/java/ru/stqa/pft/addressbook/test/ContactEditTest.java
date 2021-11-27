package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactEditTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().homePage();
        if (ap.contact().all().size() == 0) {
            ap.contact().create(new ContactData()
                    .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                    .withGroup("1"));
            ;
        }
    }

    @Test
    public void testEditContact() throws Exception {

        Set<ContactData> before = ap.contact().all();
        ContactData modifiedContact = before.iterator().next();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("zero")
                .withLastname("zero").withEmail("zero").withAddress("zero");
        ap.contact().modify(index, contact);
        Set<ContactData> after = ap.contact().all();

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}

