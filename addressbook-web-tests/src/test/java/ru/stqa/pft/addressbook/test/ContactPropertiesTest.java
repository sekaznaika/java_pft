package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPropertiesTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().homePage();
        if (ap.contact().all().size() == 0) {
            ap.contact().create(new ContactData()
                    .withFirstname("Test1").withLastname("Test2").withEmail("email1").withEmail2("email2").withEmail3("email3")
                    .withAddress("test4")
                    .withWorkPhone("111").withMobilePhone("222").withHomePhone("333"));
        }
    }


    @Test
    public void testContactProperties() {
        ap.goTo().homePage();
        ContactData contact = ap.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = ap.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmail(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPropertiesTest::cleaned)
                .collect(Collectors.joining("\n"));

    }

    static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}

