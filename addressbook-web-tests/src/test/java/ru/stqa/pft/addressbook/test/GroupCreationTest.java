package ru.stqa.pft.addressbook.test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

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
