package org.hychen39.easymail;

/**
 * @author hychen39@gm.cyut.edu.tw
 * @since 11/6/2020
 */
public class EmailEntity {
    private String recipient;
    private String sender;
    private String username;
    private String password;
    private String contents;
    private String subject;

    public String getRecipient() {
        return recipient;
    }

    public EmailEntity setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public EmailEntity setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public EmailEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public EmailEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public EmailEntity setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public EmailEntity setSubject(String subject) {
        this.subject = subject;
        return this;
    }
}
