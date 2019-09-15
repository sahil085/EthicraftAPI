package com.iskcon.EthicraftAPI.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@PropertySource("classpath:application.properties")
public class MailerService {

    @Resource
    Environment env;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;

    public void prepareAndSend(String recipient, Object message, String templateName) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            // ...
            String content = build(templateName, message);
            messageHelper.setText(content, true);
            messageHelper.setTo(recipient);
            mimeMessage.setSubject("Ethiccraft Club Membership Approved");
            messageHelper.setFrom(env.getProperty("email.default.from"));
        };
        sendEmail(messagePreparator);
    }

    public String build(String templateName, Object message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process(templateName, context);
    }

    public void sendEmail(MimeMessagePreparator preparator) {
        mailSender.send(preparator);
    }
}
