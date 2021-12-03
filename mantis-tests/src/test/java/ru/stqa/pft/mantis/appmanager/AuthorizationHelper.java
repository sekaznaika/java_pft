package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AuthorizationHelper extends HelperBase{

    public AuthorizationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password){
        type(By.id("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.id("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }


}