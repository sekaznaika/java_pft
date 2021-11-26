package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        ap.getNavigationHelper().goToGroupPage();
        List<GroupData> before = ap.getGroupHelper().getGroupList();
        ap.getGroupHelper().createGroup(new GroupData("1", null, null));
        List<GroupData> after = ap.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }


}
