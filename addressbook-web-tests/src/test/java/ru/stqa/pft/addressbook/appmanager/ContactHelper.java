package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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

    public void clickEditContactById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
        ;
    }
    public int count() {
        return wd.findElements(By.name("entry")).size();
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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());

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
        contactsCache = null;
        returnHomePage();
    }

    public void modify(ContactData contact) {
        clickEditContactById(contact.getId());
        fillTheForm(contact, false);
        updateChanges();
        contactsCache = null;
        returnHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickDeleteContact();
        contactsCache = null;
        closeDeleteAlert();
    }

    private Contacts contactsCache = null;

    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String fname = cells.get(2).getText();
            String lname = cells.get(1).getText();
            String[] phones = cells.get(5).getText().split("\n");
            contactsCache.add(new ContactData().withId(id).withFirstname(fname).withLastname(lname)
                    .withHomePhone(phones[0])
                    .withMobilePhone(phones[1])
                    .withWorkPhone(phones[2]));
        }
        return new Contacts(contactsCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        clickEditContactById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName)
                .withHomePhone(homephone).withMobilePhone(mobile).withWorkPhone(work);

    }
}
