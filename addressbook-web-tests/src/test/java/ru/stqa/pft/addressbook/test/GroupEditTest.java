package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupEditTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {

        if (ap.db().groups().size() == 0) {
            ap.goTo().groupPage();
            ap.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testEditGroup() {
        Groups before = ap.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("0").withHeader("0").withFooter("0");
        ap.goTo().groupPage();
        ap.group().modify(group);
        assertThat(ap.group().count(), equalTo(before.size()));
        Groups after = ap.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }


}
