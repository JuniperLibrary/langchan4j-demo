package org.example.langchan4jdemo.controller;

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
//import org.example.langchan4jdemo.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  @Autowired
  private OpenAiChatModel openAiChatModel;

  @Autowired
  private OllamaChatModel ollamaChatModel;

//  @Autowired
//  private Assistant assistant;


  @GetMapping("/chat")
  public String model(@RequestParam(value = "message", defaultValue = "Hello") String message) {
    return ollamaChatModel.chat(message);
  }

  @GetMapping("/chatAssistant")
  public String chat(@RequestParam(value = "message", defaultValue = "Hello") String message) {
    return ollamaChatModel.chat(message);
  }
}
