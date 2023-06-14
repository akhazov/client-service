package com.akhazov.clientservice.config;


import com.akhazov.clientservice.service.validation.field.EmailFieldValidator;
import com.akhazov.clientservice.service.validation.field.PassportIdFieldValidator;
import com.akhazov.clientservice.service.validation.field.PhoneFieldValidator;
import com.akhazov.clientservice.service.validation.request.create.client.BankCreateClientRequestValidator;
import com.akhazov.clientservice.service.validation.request.create.client.GosuslugiCreateClientRequestValidator;
import com.akhazov.clientservice.service.validation.request.create.client.MailCreateClientRequestValidator;
import com.akhazov.clientservice.service.validation.request.create.client.MobileCreateClientRequestValidator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ValidatorConfiguration {

    @Bean
    public BankCreateClientRequestValidator bank() {
        return new BankCreateClientRequestValidator();
    }

    @Bean
    public GosuslugiCreateClientRequestValidator gosuslugi() {
        return new GosuslugiCreateClientRequestValidator();
    }

    @Bean
    public MailCreateClientRequestValidator mail() {
        return new MailCreateClientRequestValidator();
    }

    @Bean
    public MobileCreateClientRequestValidator mobile() {
        return new MobileCreateClientRequestValidator();
    }

    @Bean
    public EmailFieldValidator emailFieldValidator() {
        return new EmailFieldValidator();
    }

    @Bean
    public PassportIdFieldValidator passportIdFieldValidator() {
        return new PassportIdFieldValidator();
    }

    @Bean
    public PhoneFieldValidator phoneFieldValidator() {
        return new PhoneFieldValidator();
    }

}
