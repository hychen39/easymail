package org.hychen39;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.hychen39.easymail.EmailEntity;
import org.hychen39.easymail.MailByMailtrapSMTP;
import org.hychen39.easymail.MailByServerProtocal;
import org.hychen39.easymail.SmtpHostPropertyBuilder;
import org.junit.Assert;

import java.util.Properties;
import org.junit.Test;

/**
 * @author hychen39@gm.cyut.edu.tw
 * @since 11/7/2020
 */
public class MailByMailtrapSMTPTest {

    @Test
    public void sendMail() throws FileNotFoundException, IOException {
         Properties mailtrapAccount = new Properties();
        mailtrapAccount.load(new FileInputStream("mailtrapAccount.conf"));
        String username = mailtrapAccount.getProperty("username");
        String password = mailtrapAccount.getProperty("password");
        
        // Prepare the EmailEntity
        EmailEntity email = new EmailEntity();
        email.setRecipient("user1@example.com")
                .setSender("usedbook106@gmail.com")
                .setUsername(username)
                .setPassword(password)
                .setSubject("Testing the java mail api")
                .setContents("Testing mailtrap.io smtp");
        //Create the SMTP Host Properties
        Properties props = SmtpHostPropertyBuilder.getStmpProp("smtp.mailtrap.io", "2525");

        MailByServerProtocal mailByMailtrapSMTP = new MailByMailtrapSMTP();
        mailByMailtrapSMTP.setEmailEntity(email).setSmtpHostProperties(props);

        Assert.assertTrue( mailByMailtrapSMTP.sendMail());

    }
}
