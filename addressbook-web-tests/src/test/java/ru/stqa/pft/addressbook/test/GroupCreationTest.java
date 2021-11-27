package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        ap.goTo().groupPage();
        Set<GroupData> before = ap.group().all();
        GroupData group = new GroupData().withName("1");
        ap.group().create(group);
        Set<GroupData> after = ap.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }


}
