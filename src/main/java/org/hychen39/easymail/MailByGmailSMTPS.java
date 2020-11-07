package org.hychen39.easymail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Send mail by using the Gmail SMTPS.
 *
 * Ref: http://tw.gitbook.net/javamail_api/javamail_api_gmail_smtp_server.html
 * @author hychen39@gm.cyut.edu.tw
 * @since 11/4/2020
 */
public class MailByGmailSMTPS {
    private EmailEntity emailEntity;
    private Properties smtpHostProperty;


    public EmailEntity getEmailEntity() {
        return emailEntity;
    }


    public MailByGmailSMTPS setEmailEntity(EmailEntity emailEntity) {
        this.emailEntity = emailEntity;
        return this;
    }

    public Properties getSmtpHostProperty() {
        return smtpHostProperty;
    }

    public MailByGmailSMTPS setSmtpHostProperty(Properties smtpHostProperty) {
        this.smtpHostProperty = smtpHostProperty;
        return this;
    }


    /**
     * Send mail
     */
    public boolean sendMail(){

        // Get the Session object to communicate with the smtp server
        // Need to provide the username and password for authentication
        Session session = Session.getInstance(this.smtpHostProperty,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailEntity.getUsername(), emailEntity.getPassword());
                    }
                });

        // Get smpts transportation
        Transport transport = null;
        try {
            transport = session.getTransport("smtps");
            transport.connect(smtpHostProperty.getProperty("mail.smtps.host"),
                    Integer.valueOf(smtpHostProperty.getProperty("mail.smtps.port")),
                    emailEntity.getUsername(), emailEntity.getPassword());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Create the MIME mail object
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailEntity.getSender()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailEntity.getRecipient()));
            message.setSubject(emailEntity.getSubject());
            message.setText(emailEntity.getContents());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }


    }
}
