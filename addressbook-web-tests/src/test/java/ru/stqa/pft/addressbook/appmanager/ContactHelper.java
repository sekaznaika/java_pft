package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void clickEditContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void clickDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeDeleteAlert() {
        wd.switchTo().alert().accept();
    }

    public void updateChanges() {
        click(By.name("update"));
    }


    public void submitChanges() {
        click(By.name("submit"));
    }

    public void fillTheForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

}
