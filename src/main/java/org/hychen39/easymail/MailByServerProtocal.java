package org.hychen39.easymail;

import java.util.Properties;

/**
 * @author hychen39@gm.cyut.edu.tw
 * @since 11/7/2020
 */
abstract public class MailByServerProtocal {
    private EmailEntity emailEntity;
    private Properties smtpHostProperties;

    public EmailEntity getEmailEntity() {
        return emailEntity;
    }

    public MailByServerProtocal setEmailEntity(EmailEntity emailEntity) {
        this.emailEntity = emailEntity;
        return this;
    }

    public Properties getSmtpHostProperties() {
        return smtpHostProperties;
    }

    public MailByServerProtocal setSmtpHostProperties(Properties smtpHostProperties) {
        this.smtpHostProperties = smtpHostProperties;
        return this;
    }

    abstract public  boolean sendMail();
}
