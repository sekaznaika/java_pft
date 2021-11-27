package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupEditTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        ap.goTo().groupPage();
        if (ap.group().list().size() == 0) {
            ap.group().create(new GroupData("1", null, null));
        }
    }

    @Test
    public void testDeleteGroup() throws Exception {
        List<GroupData> before = ap.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "0", "0", "0");
        ap.group().modify(index, group);
        List<GroupData> after = ap.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
