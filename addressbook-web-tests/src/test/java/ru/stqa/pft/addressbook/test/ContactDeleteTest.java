package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().homePage();
        if (ap.db().contacts().size() == 0) {
            ap.contact().create(new ContactData()
                    .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                    .withGroup("1").withWorkPhone("111").withMobilePhone("222").withHomePhone("333"));
        }
    }

    @Test
    public void deleteContactTest() throws Exception {
        Contacts before = ap.db().contacts();
        ContactData deletedContact = before.iterator().next();
        ap.contact().delete(deletedContact);
        ap.goTo().homePage();
        assertEquals(ap.contact().count(), before.size()-1);
        Contacts after = ap.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
