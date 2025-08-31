package org.example.langchan4jdemo.controller;

import org.example.langchan4jdemo.assistant.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIServiceController {
  @Autowired
  private Assistant assistant;

  @GetMapping("/testChat")
  public String testChat(@RequestParam(value = "message", defaultValue = "Hello") String message) {
//    //创建AIService
//    Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
    //调用service的接口
    return assistant.chat(message);
  }
}
