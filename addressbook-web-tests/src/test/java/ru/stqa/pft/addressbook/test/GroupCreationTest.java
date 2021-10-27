package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        applicationManager.goToGroupPage();
        applicationManager.initGroupCreation();
        applicationManager.fillGroupForm(new GroupData("1", "2", "3"));
        applicationManager.submitGroupCreation();
        applicationManager.returnGroupPage();
    }


}
