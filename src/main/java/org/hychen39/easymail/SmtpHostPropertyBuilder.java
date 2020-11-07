package org.hychen39.easymail;

import java.util.Optional;
import java.util.Properties;

/**
 * Create the property for the specific smtp host
 * @author hychen39@gm.cyut.edu.tw
 * @since 11/4/2020
 */
public class SmtpHostPropertyBuilder {

    /**
     * Gmail SMTPS 的 properties 設定
     * 外寄郵件 (SMTP) 伺服器  smtp.gmail.com.
     * 需要使用 smtps 協定
     * 需要安全資料傳輸層 (SSL)：是
     * 需要驗證：是
     * 安全資料傳輸層 (SSL) 通訊埠：465
     *
     * @param host SMTP host. Default to smtp.gmail.com when null.
     * @param port SMTPS port. Default to 465 when null.
     * Ref:
     * - https://support.google.com/mail/answer/7126229?hl=zh-Hant
     * - https://javaee.github.io/javamail/docs/api/com/sun/mail/smtp/package-summary.html
     * @return
     */
    public static Properties getGmailSmtpsProp(String host, String port){
        Properties prop = new Properties();
        String l_host = Optional.ofNullable(host).orElse("smtp.gmail.com");
        String d_port = Optional.ofNullable(port).orElse("465");
        // set the default user
        prop.put("mail.smtps.auth", "true");
        prop.put("mail.smtps.startls.enable", "true");
        // can be specified when connecting
        prop.put("mail.smtps.host", l_host);
        // TLS PORT
        prop.put("mail.smtps.port", d_port);
//        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtps.socketFactory.port", d_port);
        prop.put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtps.socketFactory.fallback", "false");

        return prop;
    }

    /**
     * Get SMTP host property.
     * auth: true
     * starttls: true
     *
     * @param host default SMTP hots
     * @param port default SMTP port
     * @return
     */
    public static Properties getStmpProp(String host, String port){
        port = Optional.ofNullable(port).orElse("587");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        return properties;
    }
}
