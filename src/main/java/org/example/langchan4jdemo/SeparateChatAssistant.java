package org.example.langchan4jdemo;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService(
    wiringMode = EXPLICIT,
    chatMemory = "chatMemory",
    chatMemoryProvider = "chatMemoryProvider",
    chatModel = "qwenChatModel"
)
public interface SeparateChatAssistant {

  /**
   * 分离聊天记录
   *
   * @param memoryId    聊天id
   * @param userMessage 用户消息
   * @return
   */
  String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
