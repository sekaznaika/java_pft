package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupEditTest extends TestBase {

    @Test
    public void testDeleteGroup() throws Exception {
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().editSelectedGroup();
        applicationManager.getGroupHelper().fillGroupForm(new GroupData("0", "0", "0"));
        applicationManager.getGroupHelper().updateGroup();
        applicationManager.getGroupHelper().returnGroupPage();
    }
}
