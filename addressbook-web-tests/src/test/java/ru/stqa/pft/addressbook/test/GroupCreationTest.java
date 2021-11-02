package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().initGroupCreation();
        applicationManager.getGroupHelper().fillGroupForm(new GroupData("1", "2", "3"));
        applicationManager.getGroupHelper().submitGroupCreation();
        applicationManager.getGroupHelper().returnGroupPage();
    }


}
