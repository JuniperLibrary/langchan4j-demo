package org.example.langchan4jdemo.controller;

import org.example.langchan4jdemo.assistant.SeparateChatAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeparateChatController {
  @Autowired
  private SeparateChatAssistant separateChatAssistant;

  @GetMapping("/testSeparateChat")
  public void tesSeparateChat() {
    String answer1 = separateChatAssistant.chat(1,"我是环环");
    System.out.println(answer1);
    String answer2 = separateChatAssistant.chat(1,"我是谁");
    System.out.println(answer2);
    String answer3 = separateChatAssistant.chat(2,"我是谁");
    System.out.println(answer3);
  }
}
