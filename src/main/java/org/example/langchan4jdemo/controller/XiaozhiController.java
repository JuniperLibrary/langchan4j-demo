package org.example.langchan4jdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.langchan4jdemo.assistant.XiaozhiAgent;
import org.example.langchan4jdemo.dto.ChatForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "硅谷小智")
@RestController
@RequestMapping("/xiaozhi")
public class XiaozhiController {

  @Autowired
  private XiaozhiAgent xiaozhiAgent;

  @Operation(summary = "对话")
  @PostMapping("/chat1")
  public String chat1(@RequestBody ChatForm chatForm) {
    return xiaozhiAgent.chat1(chatForm.getMemoryId(), chatForm.getMessage());
  }

  @Operation(summary = "对话")
  @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
  public Flux<String> chat(@RequestBody ChatForm chatForm)  {
    return xiaozhiAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
  }
}
