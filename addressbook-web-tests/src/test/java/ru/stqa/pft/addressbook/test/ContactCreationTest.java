package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {


    @Test
    public void testCreateContact() throws Exception {
        Contacts before = ap.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                .withGroup("1");
        ap.contact().create(contact);
        Contacts after = ap.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
