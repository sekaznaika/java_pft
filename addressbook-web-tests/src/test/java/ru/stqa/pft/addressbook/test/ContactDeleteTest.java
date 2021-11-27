package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().homePage();
        if (ap.contact().all().size() == 0) {
            ap.contact().create(new ContactData()
                    .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                    .withGroup("1"));
        }
    }

    @Test
    public void deleteContactTest() throws Exception {
        Set<ContactData> before = ap.contact().all();
        ContactData deletedContact = before.iterator().next();
        ap.contact().delete(deletedContact);
        ap.goTo().homePage();
        Set<ContactData> after = ap.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


}
