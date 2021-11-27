package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

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

        Set<GroupData> before = ap.group().all();
        GroupData deletedGroup = before.iterator().next();
        ap.group().delete(deletedGroup);
        Set<GroupData> after = ap.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }


}
