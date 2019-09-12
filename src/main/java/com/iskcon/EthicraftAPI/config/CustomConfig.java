package com.iskcon.EthicraftAPI.config;

import javax.annotation.Resource;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class CustomConfig implements WebMvcConfigurer {

    @Resource
    private Environment env;

    @Bean
    public ClassLoaderTemplateResolver yourTemplateResolver() {
        ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
        configurer.setPrefix("templates/");
        configurer.setSuffix(".html");
        configurer.setTemplateMode(TemplateMode.HTML);
        configurer.setCharacterEncoding("UTF-8");
        configurer.setOrder(0);  // this is important. This way spring //boot will listen to both places 0 and 1
        configurer.setCheckExistence(true);
    return configurer;
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getRequiredProperty("email.service.host"));
        mailSender.setPort(Integer.parseInt(env.getRequiredProperty("email.service.port")));
        mailSender.setUsername(env.getRequiredProperty("email.service.admin.name"));
        mailSender.setPassword(env.getRequiredProperty("email.service.admin.password"));
        System.out.println("----------------------  "+mailSender.getPassword());

        Properties javaMailProperties = new Properties();
        if ("smtp.gmail.com".equalsIgnoreCase(env.getRequiredProperty("email.service.host"))) {
            javaMailProperties.put("mail.smtp.starttls.enable", "true");
            javaMailProperties.put("mail.smtp.auth", "true");
            javaMailProperties.put("mail.transport.protocol", "smtp");
            javaMailProperties.put("mail.debug", "true");
        } else {
            javaMailProperties.put("mail.smtp.starttls.enable", "true");
            javaMailProperties.put("mail.smtp.port", Integer.parseInt(env.getRequiredProperty("email.service.port")));
            javaMailProperties.put("mail.smtp.auth", "true");
            javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
        }
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
