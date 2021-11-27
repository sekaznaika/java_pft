package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().groupPage();
        if (ap.group().all().size() == 0) {
            ap.group().create(new GroupData().withName("1"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        Groups before = ap.group().all();
        GroupData deletedGroup = before.iterator().next();
        ap.group().delete(deletedGroup);
        Groups after = ap.group().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));
    }


}
