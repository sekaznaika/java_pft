package ru.stqa.pft.mantis.tests;

import org.testng.annotations.*;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.LinkHelper;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTestsWithInnerMail extends TestBase {

    private String adminLogin;
    private String adminPassword;

    @BeforeMethod
    public void ensurePreconditions() {
        adminLogin = app.getProperty("web.adminLogin");
        adminPassword = app.getProperty("web.adminPassword");
        app.authorization().login(adminLogin, adminPassword);
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        app.goTo().manageUserPage();

        UserData user = app.db().users().stream().filter((u) -> !u.getUsername().equals(adminLogin)).findFirst().get();

        app.user().openUser(user).resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String setLink = LinkHelper.findConfirmationLink(mailMessages, user.getEmail());
        long now = System.currentTimeMillis();
        String newPass = String.format("test%s", now);

        app.setPassword().openPage(setLink).set(newPass);

        assertTrue(app.newSession().login(user.getUsername(), newPass));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMail() {
        app.mail().stop();
    }



}