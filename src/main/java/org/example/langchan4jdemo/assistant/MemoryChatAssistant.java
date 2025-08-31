package org.example.langchan4jdemo.assistant;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService(
    wiringMode = EXPLICIT,
    chatModel = "qwenChatModel",
    chatMemory = "chatMemory"
)
public interface MemoryChatAssistant {
  @UserMessage("你是我的好朋友，请用上海话回答问题，并且添加一些表情符号。 {{it}}") //{{it}}表示这里唯一的参数的占位符
  String chat(String message);

  @UserMessage("你是我的好朋友，请用北京话回答问题，并且添加一些表情符号。 {{m}}") //{{it}}表示这里唯一的参数的占位符
  String chat2(@V("m") String message);
}
