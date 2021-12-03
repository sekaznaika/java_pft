package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUserPage(){
        if (wd.findElement(By.id("menu-toggler")).isDisplayed()) {
            click(By.id("menu-toggler"));
        }
        click(By.partialLinkText("Управление"));
        click(By.partialLinkText("Управление пользователями"));
    }
}