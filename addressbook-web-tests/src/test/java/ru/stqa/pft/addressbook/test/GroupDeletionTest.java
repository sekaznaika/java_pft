package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    applicationManager.goToGroupPage();
    applicationManager.selectGroup();
    applicationManager.deleteSelectedGroup();
    applicationManager.returnGroupPage();
  }

}
