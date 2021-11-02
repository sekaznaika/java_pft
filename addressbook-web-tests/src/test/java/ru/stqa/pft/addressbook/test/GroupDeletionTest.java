package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    applicationManager.getNavigationHelper().goToGroupPage();
    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getGroupHelper().deleteSelectedGroup();
    applicationManager.getGroupHelper().returnGroupPage();
  }

}
