package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().groupPage();
        if (ap.group().list().size()==0) {
            ap.group().create(new GroupData("1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        List<GroupData> before = ap.group().list();
        int index = before.size() - 1;
        ap.group().delete(index);
        List<GroupData> after = ap.group().list();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(after.size() - 1);
        Assert.assertEquals(before, after);
    }



}
