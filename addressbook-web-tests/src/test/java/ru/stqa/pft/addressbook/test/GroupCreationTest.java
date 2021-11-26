package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        ap.getNavigationHelper().goToGroupPage();
        int before = ap.getGroupHelper().getGroupCount();
        ap.getGroupHelper().createGroup(new GroupData("1", null, null));
        int after = ap.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }


}
