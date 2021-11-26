package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        ap.getNavigationHelper().goToGroupPage();
        if (!ap.getGroupHelper().isThereAGroup()) {
            ap.getGroupHelper().createGroup(new GroupData("1", null, null));
        }
        int before = ap.getGroupHelper().getGroupCount();
        ap.getGroupHelper().selectGroup();
        ap.getGroupHelper().deleteSelectedGroup();
        ap.getGroupHelper().returnGroupPage();
        int after = ap.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
    }

}
