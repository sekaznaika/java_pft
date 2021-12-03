package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SetPasswordHelper extends HelperBase{

    public SetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public SetPasswordHelper openPage(String confirmationLink) {
        wd.get(confirmationLink);
        return this;
    }

    public void set(String password) {
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

}