package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTest extends TestBase {


    @Test
    public void testCreateContact() throws Exception {
        Contacts before = ap.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                .withGroup("1").withWorkPhone("111").withMobilePhone("222").withHomePhone("333");
        ap.contact().create(contact);
        assertEquals(ap.contact().count(), before.size() + 1);
        Contacts after = ap.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
