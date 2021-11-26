package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupEditTest extends TestBase {

    @Test
    public void testDeleteGroup() throws Exception {
        ap.getNavigationHelper().goToGroupPage();
        if (!ap.getGroupHelper().isThereAGroup()) {
            ap.getGroupHelper().createGroup(new GroupData("1", null, null));
        }
        List<GroupData> before = ap.getGroupHelper().getGroupList();
        ap.getGroupHelper().selectGroup(before.size() - 1);
        ap.getGroupHelper().editSelectedGroup();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "0", "0", "0");
        ap.getGroupHelper().fillGroupForm(group);
        ap.getGroupHelper().updateGroup();
        ap.getGroupHelper().returnGroupPage();
        List<GroupData> after = ap.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
