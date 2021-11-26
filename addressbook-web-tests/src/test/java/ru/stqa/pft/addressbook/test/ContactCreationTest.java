package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends TestBase {


    @Test
    public void testCreateContact() throws Exception {
        ap.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test3", "test4", "Test5", "1"));
    }


}
