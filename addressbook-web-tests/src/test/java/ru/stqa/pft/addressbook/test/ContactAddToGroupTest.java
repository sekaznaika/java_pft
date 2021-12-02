package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.NoSuchElementException;

public class ContactAddToGroupTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (ap.db().contacts().size() == 0) {
            ap.goTo().homePage();
            ap.contact().create(new ContactData()
                    .withFirstname("Fn1").withLastname("LN1").withMobilePhone("999")
                    .withEmail("Em1"));
        }

        if (ap.db().groups().size() == 0) {
            ap.goTo().groupPage();
            ap.group().create(new GroupData().withName("name 1").withHeader("header 1").withFooter("footer 1"));
            ap.goTo().homePage();
        }

    }

@Test
public void testAddContactToGroup() {
        GroupData groupForAdding = ap.db().groups().iterator().next();
        ContactData before;
        try {
        before = ap.db().contacts().stream().filter((c) -> !c.getGroups().contains(groupForAdding)).findFirst().get();
        }
        catch (NoSuchElementException e) {
        ap.goTo().homePage();
        before = prepareContact().iterator().next();
        }

        ap.contact().addContactToGroup(before, groupForAdding);

        ContactData finalBefore = before;
        ContactData after = ap.db().contacts().stream().filter((c) -> c.getId() == finalBefore.getId()).findFirst().get();

        assert(after.getGroups().contains(groupForAdding));
        }

public Contacts prepareContact() {
        Contacts before = ap.db().contacts();
        ap.goTo().homePage();
        ap.contact().create(new ContactData()
        .withFirstname("FnTest").withLastname("LnTest").withMobilePhone("99999")
        .withEmail("EmTest"));
        Contacts after = ap.db().contacts();
        after.removeAll(before);
        return after;
        }
}