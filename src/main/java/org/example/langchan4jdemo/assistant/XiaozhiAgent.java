package org.example.langchan4jdemo.assistant;

import dev.langchain4j.service.*;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(
    wiringMode = EXPLICIT,
//    chatModel = "qwenChatModel",
    streamingChatModel = "qwenStreamingChatModel",
    chatMemoryProvider = "chatMemoryProviderXiaozhi",
    tools = "appointmentTools",
//    contentRetriever = "contentRetrieverXiaozhi"
    contentRetriever = "contentRetrieverXiaozhiPincone"
)
public interface XiaozhiAgent {

  @SystemMessage(fromResource = "zhaozhi-prompt-template.txt")
  String chat1(@MemoryId Long memoryId, @UserMessage String userMessage);

  @SystemMessage(fromResource = "zhaozhi-prompt-template.txt")
  Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
