package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupEditTest extends TestBase {

    @Test
    public void testDeleteGroup() throws Exception {
        ap.getNavigationHelper().goToGroupPage();
        if (!ap.getGroupHelper().isThereAGroup()) {
            ap.getGroupHelper().createGroup(new GroupData("1", null, null));
        }
        int before = ap.getGroupHelper().getGroupCount();
        ap.getGroupHelper().selectGroup(before - 1);
        ap.getGroupHelper().editSelectedGroup();
        ap.getGroupHelper().fillGroupForm(new GroupData("0", "0", "0"));
        ap.getGroupHelper().updateGroup();
        ap.getGroupHelper().returnGroupPage();
        int after = ap.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
