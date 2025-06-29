package com.meuDiario.diary.service.SmsService;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String phoneNumber;

    protected void sendSMS(String to, String message){
        Twilio.init(accountSid, authToken);

        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(phoneNumber),
                message
        ).create();
    }

}
