package org.hychen39.easymail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author hychen39@gm.cyut.edu.tw
 * @since 11/7/2020
 */
public class MailByMailtrapSMTP extends MailByServerProtocal{

    @Override
    public boolean sendMail() {
//        this.getEmailEntity().setUsername("890ac44f3d6665");
//        this.getEmailEntity().setPassword("266f8ebf80175e");
//        String username = "890ac44f3d6665";
        String username = this.getEmailEntity().getUsername();

//        String password = "266f8ebf80175e";
        String password = this.getEmailEntity().getPassword();


        // create the property
//        Properties props = SmtpHostPropertyBuilder.getStmpProp(
//                "smtp.mailtrap.io",
//                "2525"
//        );
        Properties props = this.getSmtpHostProperties();
        // create the session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create the message with the session object
        Message message = new MimeMessage(session);
        EmailEntity email = getEmailEntity();
        try {
            message.setFrom(new InternetAddress(email.getSender()));
            // Send to many recipients
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(email.getRecipient()));
            message.setSubject(email.getSubject());
            message.setText(email.getContents());
            // Transport the message
            Transport.send(message, message.getAllRecipients());
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}

