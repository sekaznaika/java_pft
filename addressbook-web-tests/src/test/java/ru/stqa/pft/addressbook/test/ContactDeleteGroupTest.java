package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().homePage();
        if (ap.db().contacts().size() == 0) {
            ap.contact().create(new ContactData()
                    .withFirstname("Fn1").withLastname("Ln1").withMobilePhone("11")
                    .withEmail("Em1"));
            ap.goTo().homePage();
        }
        ap.goTo().groupPage();
        if (ap.db().groups().size() == 0) {
            ap.group().create(new GroupData().withName("test1").withHeader("header").withFooter("footer"));
            ap.goTo().groupPage();
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        GroupData group = selectGroup();
        Contacts groupContactsBefore = ap.db().contactsInGroup(group.getId());
        ContactData contact = groupContactsBefore.iterator().next();
        ap.goTo().homePage();
        ap.contact().groupSelect(group.getId());
        ap.contact().removeFromGroup(contact);
        Contacts groupContactsAfter = ap.db().contactsInGroup(group.getId());
        assertEquals(groupContactsAfter.size(), groupContactsBefore.size() - 1);
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(ap.db().contactById(contact.getId()))));

    }

    private GroupData selectGroup() {
        Groups groups = ap.db().groups();
        for (GroupData group : groups) {
            if (ap.db().contactsInGroup(group.getId()).size() > 0) {
                return group;
            }
        }
        GroupData group = groups.iterator().next();
        Contacts contacts = ap.db().contacts();
        ap.goTo().homePage();
        ap.contact().addContactToGroup(contacts.iterator().next(), group);
        ap.goTo().homePage();
        return group;
    }
}