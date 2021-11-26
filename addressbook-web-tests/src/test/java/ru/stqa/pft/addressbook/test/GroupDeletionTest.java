package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        ap.getNavigationHelper().goToGroupPage();
        if (!ap.getGroupHelper().isThereAGroup()) {
            ap.getGroupHelper().createGroup(new GroupData("1", null, null));
        }
        List<GroupData> before = ap.getGroupHelper().getGroupList();
        ap.getGroupHelper().selectGroup(before.size() - 1);
        ap.getGroupHelper().deleteSelectedGroup();
        ap.getGroupHelper().returnGroupPage();
        List<GroupData> after = ap.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(after.size() - 1);
        Assert.assertEquals(before,after);
    }

}
