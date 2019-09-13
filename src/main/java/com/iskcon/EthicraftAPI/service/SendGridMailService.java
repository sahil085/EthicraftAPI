package com.iskcon.EthicraftAPI.service;

import javax.annotation.Resource;

import java.io.IOException;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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

    public void sendEmailUsingSendGrid(){
        Email from = new Email("app146048085@heroku.com");
        String subject = "Hello World from the SendGrid Java Library!";
        Email to = new Email("vermasahil269@gmail.com");
        Content content = new Content("text/plain", "Hello, Email!");
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
