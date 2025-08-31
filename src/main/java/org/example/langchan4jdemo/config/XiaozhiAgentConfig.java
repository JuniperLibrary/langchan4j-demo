package org.example.langchan4jdemo.config;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiaozhiAgentConfig {

  @Autowired
  private PersistentChatMemoryStore persistentChatMemoryStore;

  @Bean
  ChatMemoryProvider chatMemoryProviderXiaozhi() {
    return memoryId -> MessageWindowChatMemory.builder()
        .id(memoryId)
        .maxMessages(20)
        .chatMemoryStore(persistentChatMemoryStore)
        .build();
  }
}
