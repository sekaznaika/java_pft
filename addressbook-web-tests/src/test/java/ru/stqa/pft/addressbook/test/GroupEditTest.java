package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupEditTest extends TestBase {

    @Test
    public void testDeleteGroup() throws Exception {
        applicationManager.getNavigationHelper().goToGroupPage();
        if (!applicationManager.getGroupHelper().isThereAGroup()) {
            applicationManager.getGroupHelper().createGroup(new GroupData("1", null, null));
        }
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().editSelectedGroup();
        applicationManager.getGroupHelper().fillGroupForm(new GroupData("0", "0", "0"));
        applicationManager.getGroupHelper().updateGroup();
        applicationManager.getGroupHelper().returnGroupPage();
    }
}
