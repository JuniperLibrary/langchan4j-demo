package org.example.langchan4jdemo.assistant;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService(
    wiringMode = EXPLICIT,
    chatMemory = "chatMemory",
    chatMemoryProvider = "chatMemoryProvider",
    chatModel = "qwenChatModel",
    tools = "calculatorTools" //配置tools
)
public interface SeparateChatAssistant {

  /**
   * 分离聊天记录
   *
   * @param memoryId    聊天id
   * @param userMessage 用户消息
   * @return
   */
//  @SystemMessage("你是我的好朋友，请用陕西话回答问题")
//  @SystemMessage("你是我的好朋友，请用东北话回答问题。今天是{{current_date}}")//系统消息提示词
  @SystemMessage(fromResource = "my-prompt-template.txt")
  String chat(@MemoryId int memoryId, @UserMessage String userMessage);

  @UserMessage("你是我的好朋友，请用上海话回答问题，并且添加一些表情符号。{{message}}")
  String chat2(@V("message") String userMessage);

  @UserMessage("你是我的好朋友，请用粤语回答问题。{{message}}")
  String chat2(@MemoryId int memoryId, @V("message") String userMessage);


  @SystemMessage(fromResource = "my-prompt-template.txt")
  String chat3(
      @MemoryId int memoryId,
      @UserMessage String userMessage,
      @V("username") String username,
      @V("age") int age
  );
}
