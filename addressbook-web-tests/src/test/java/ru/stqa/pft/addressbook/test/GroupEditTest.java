package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupEditTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().groupPage();
        if (ap.group().all().size() == 0) {
            ap.group().create(new GroupData().withName("1"));
        }
    }

    @Test
    public void testDeleteGroup() throws Exception {
        Set<GroupData> before = ap.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("0").withHeader("0").withFooter("0");
        ap.group().modify(group);
        Set<GroupData> after = ap.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(before, after);
    }


}
