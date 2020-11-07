package org.hychen39;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.Assert;
import org.hychen39.easymail.EmailEntity;
import org.hychen39.easymail.MailByGmailSMTPS;
import org.hychen39.easymail.SmtpHostPropertyBuilder;
import org.junit.Test;


/**
 * @author hychen39@gm.cyut.edu.tw
 * @since 11/6/2020
 */
public class MailByGmailSMTPSTest {

    @Test
    public void sendMail() throws IOException {
        
        Properties gmailAccount = new Properties();
        gmailAccount.load(new FileInputStream("gmailAccount.conf"));
        String username = gmailAccount.getProperty("username");
        String password = gmailAccount.getProperty("password");
        System.out.printf("Username and password: %s %s", username, password);
        // create the email entity
        EmailEntity email = new EmailEntity();
        email.setRecipient("hychen39@gmail.com")
                .setSender("usedbook106@gmail.com")
                .setUsername(username)
                .setPassword(password)
                .setSubject("Testing the java mail api")
                .setContents("Test gmail smtp");

        // Create the mailer
        MailByGmailSMTPS mailByGmailSMTPS = new MailByGmailSMTPS();
        mailByGmailSMTPS.setEmailEntity(email);
        mailByGmailSMTPS.setSmtpHostProperty(
                SmtpHostPropertyBuilder.getGmailSmtpsProp("smtp.gmail.com", "465" ));
        
       Assert.assertTrue(mailByGmailSMTPS.sendMail());
    }
}
