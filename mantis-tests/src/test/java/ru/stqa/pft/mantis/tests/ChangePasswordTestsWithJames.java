package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.LinkHelper;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTestsWithJames extends TestBase {
    private String adminLogin;
    private String adminPassword;

    @BeforeMethod
    public void ensurePreconditions() {
        adminLogin = app.getProperty("web.adminLogin");
        adminPassword = app.getProperty("web.adminPassword");
        app.authorization().login(adminLogin, adminPassword);
        app.james().initTelnetSession();
    }

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        app.goTo().manageUserPage();

        UserData user = app.db().users().stream().filter((u) -> !u.getUsername().equals(adminLogin)).findFirst().get();
        String username = user.getUsername();
        String userpass = user.getUsername();
        List<MailMessage> MailBeforeReset = app.james().getAllMail(username, userpass);

        app.user().openUser(user).resetPassword();

        List<MailMessage> mailMessages = app.james().waitForMail(username, userpass, MailBeforeReset, 60000);
        String setLink = LinkHelper.findConfirmationLink(mailMessages, user.getEmail());

        long now = System.currentTimeMillis();
        String newPass = String.format("test%s", now);

        app.setPassword().openPage(setLink).set(newPass);
        assertTrue(app.newSession().login(username, newPass));
    }

    @AfterMethod(alwaysRun = true)
    public void stopJames() {
        app.james().closeTelnetSession();
    }

}