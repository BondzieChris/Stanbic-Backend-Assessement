package com.stanbic.customerapi.twilio;

import org.springframework.stereotype.Repository;

@Repository
public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    // or maybe void sendSms(String phoneNumber, String message);
}