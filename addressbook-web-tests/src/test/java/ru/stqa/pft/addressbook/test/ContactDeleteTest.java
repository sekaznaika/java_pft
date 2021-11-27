package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().homePage();
        if (ap.contact().list().size()==0) {
            ap.contact().create(new ContactData()
                    .withFirstname("Test1").withLastname("Test2").withEmail("Test3").withAddress("test4")
                    .withGroup("1"));
        }
    }

    @Test
    public void deleteContactTest() throws Exception {
        List<ContactData> before = ap.contact().list();
        int index = before.size()-1;
        ap.contact().delete(index);
        ap.goTo().homePage();
        List<ContactData> after = ap.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
