package com.atk.notificationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @GetMapping("/")
    public String getNotification() {
        return "UP!";
    }

 /*   @GetMapping("/deadletters")
    public List<String> getDeadLetters() {
        // DLQ mesajlarını veritabanından veya bir koleksiyondan döndürebilirsiniz
        return deadLetterRepository.findAllMessages();
    }*/

}
