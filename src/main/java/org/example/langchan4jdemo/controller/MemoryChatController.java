package org.example.langchan4jdemo.controller;

import org.example.langchan4jdemo.assistant.MemoryChatAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoryChatController {

  @Autowired
  private MemoryChatAssistant memoryChatAssistant;

  @GetMapping("/testChatMemory")
  public void testChatMemory4() {
    String answer1 = memoryChatAssistant.chat("我是环环");
    System.out.println(answer1);
    String answer2 = memoryChatAssistant.chat("我是谁");
    System.out.println(answer2);
  }
}
