package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void clickEditContact(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
        ;
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
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void goToAddNewContact() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        goToAddNewContact();
        fillTheForm(contact, true);
        submitChanges();
        returnHomePage();
    }
    public void modify(int index, ContactData contact) {
        clickEditContact(index);
        fillTheForm(contact, false);
        updateChanges();
        returnHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickDeleteContact();
        closeDeleteAlert();
    }


    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String fname = cells.get(2).getText();
            String lname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(fname).withLastname(lname);
            contacts.add(contact);
        }
        return contacts;
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String fname = cells.get(2).getText();
            String lname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(fname).withLastname(lname);
            contacts.add(contact);
        }
        return contacts;
    }


}
