package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        ap.getNavigationHelper().goToGroupPage();
        List<GroupData> before = ap.getGroupHelper().getGroupList();
        GroupData group = new GroupData("1", null, null);
        ap.getGroupHelper().createGroup(group);
        List<GroupData> after = ap.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);


        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
