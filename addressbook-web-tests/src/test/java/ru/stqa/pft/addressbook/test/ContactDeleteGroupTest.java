package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeleteGroupTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {

        if (ap.db().groups().size() == 0) {
            ap.goTo().groupPage();
            ap.group().create(new GroupData().withName("name 1").withHeader("header 1").withFooter("footer 1"));
            ap.goTo().homePage();
        }

        if (ap.db().contacts().size() == 0) {
            ap.goTo().homePage();
            ap.contact().create(new ContactData()
                    .withFirstname("Fn1").withLastname("Ln1").withMobilePhone("11")
                    .withEmail("Em1").inGroup(ap.db().groups().iterator().next()));
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        ContactData before;

        try {
            before = ap.db().contacts().stream().filter((c) -> c.getGroups().size() != 0).findFirst().get();
        } catch (NoSuchElementException e) {
            before = prepareContactForRemoveFromGroup(ap.db().contacts().iterator().next());
        }

        GroupData groupForRemove = before.getGroups().iterator().next();
        ap.contact().removeFromGroup(before, groupForRemove);
        ContactData finalBefore = before;
        ContactData after = ap.db().contacts().stream().filter((c) -> c.getId() == finalBefore.getId()).findFirst().get();

        assert(!after.getGroups().contains(groupForRemove));
    }

    public ContactData prepareContactForRemoveFromGroup(ContactData contact) {
        ap.contact().addContactToGroup(contact, ap.db().groups().iterator().next());
        ap.goTo().homePage();
        return ap.db().contacts().stream().filter((c) -> c.getId() == contact.getId()).findFirst().get();
    }
}
