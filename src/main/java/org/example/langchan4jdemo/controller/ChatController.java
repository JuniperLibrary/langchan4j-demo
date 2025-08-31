package org.example.langchan4jdemo.controller;

//import org.example.langchan4jdemo.assistant.Assistant;
import org.example.langchan4jdemo.assistant.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

//  @Autowired
//  private OpenAiChatModel openAiChatModel;
//
//  @Autowired
//  private OllamaChatModel ollamaChatModel;
//
//  @Autowired
//  private QwenChatModel qwenChatModel;

  @Autowired
  private Assistant assistant;


  @GetMapping("/chat")
  public String model(@RequestParam(value = "message", defaultValue = "Hello") String message) {
//     ollamaChatModel.chat(message);
//    Assistant assistant = AiServices.create(Assistant.class, ollamaChatModel);
    return assistant.chat(message);
  }

  @GetMapping("/chatAssistant")
  public String chat(@RequestParam(value = "message", defaultValue = "Hello") String message) {
//    Assistant assistant = AiServices.create(Assistant.class, ollamaChatModel);
    return assistant.chat(message);
  }
}
