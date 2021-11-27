package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        ap.goTo().groupPage();
        Groups before = ap.group().all();
        GroupData group = new GroupData().withName("1");
        ap.group().create(group);
        assertThat(ap.group().count(), equalTo(before.size() + 1));
        Groups after = ap.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() throws Exception {
        ap.goTo().groupPage();
        Groups before = ap.group().all();
        GroupData group = new GroupData().withName("test2'");
        ap.group().create(group);
        assertThat(ap.group().count(), equalTo(before.size()));
        Groups after = ap.group().all();
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before));
    }

}
