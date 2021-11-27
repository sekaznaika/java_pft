package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        ap.getNavigationHelper().goToGroupPage();
        if (!ap.getGroupHelper().isThereAGroup()) {
            ap.getGroupHelper().createGroup(new GroupData("1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        List<GroupData> before = ap.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        ap.getGroupHelper().selectGroup(index);
        ap.getGroupHelper().deleteSelectedGroup();
        ap.getGroupHelper().returnGroupPage();
        List<GroupData> after = ap.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), index);

        before.remove(after.size() - 1);
        Assert.assertEquals(before, after);
    }

}
