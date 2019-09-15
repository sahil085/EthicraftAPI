package com.iskcon.EthicraftAPI.service;

import javax.annotation.Resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class SendGridMailService {


    @Resource
    Environment environment;
    @Autowired
    private TemplateEngine templateEngine;

    public String build(String templateName, Object data) {
        Context context = new Context();
        context.setVariable("data", data);
        return templateEngine.process(templateName, context);
    }

    public void sendEmailUsingSendGrid(String subject, Object data, String templateName, String recipient){
        Email from = new Email("app146048085@heroku.com");
        Email to = new Email(recipient);
        Content content = new Content("text/html", build(templateName, data));
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(environment.getProperty("send.grid.api.key"));
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
