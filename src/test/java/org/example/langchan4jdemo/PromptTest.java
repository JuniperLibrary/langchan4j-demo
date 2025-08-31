package org.example.langchan4jdemo;

import org.example.langchan4jdemo.assistant.MemoryChatAssistant;
import org.example.langchan4jdemo.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {

  @Autowired
  private SeparateChatAssistant separateChatAssistant;

  @Test
  public void testSystemMessage() {
    String answer = separateChatAssistant.chat(3,"今天几号");
    System.out.println(answer);
  }

  @Test
  public void testSystemMessage2() {
    String answer = separateChatAssistant.chat(3,"你是瓜皮吗");
    System.out.println(answer);
  }

  @Test
  public void testSystemMessage3() {
    String answer = separateChatAssistant.chat(5,"今天几号");
    System.out.println(answer);
  }

  @Test
  public void testSystemMessage4() {
    String answer = separateChatAssistant.chat(5,"今天几号");
    System.out.println(answer);
  }

  @Autowired
  private MemoryChatAssistant memoryChatAssistant;

  @Test
  public void testUserMessage() {
    String answer = memoryChatAssistant.chat("我是环环");
    System.out.println(answer);
    String answer2 = memoryChatAssistant.chat("我是谁");
    System.out.println(answer2);
    String answer3 = memoryChatAssistant.chat("你是瓜皮吗");
    System.out.println(answer3);
    String answer4 = memoryChatAssistant.chat("今天几号");
    System.out.println(answer4);
    String answer5 = memoryChatAssistant.chat("今天几号");
    System.out.println(answer5);
  }

  @Test
  public void testV() {
    String answer1 = separateChatAssistant.chat2(6, "我是环环");
    System.out.println(answer1);
    String answer2 = separateChatAssistant.chat2(6, "我是谁");
    System.out.println(answer2);

    String answer3 = separateChatAssistant.chat2( "我是谁");
    System.out.println(answer3);
  }


  @Test
  public void testUserInfo() {
    String answer = separateChatAssistant.chat3(1, "我是谁，我多大了", "翠花", 18);
    System.out.println(answer);
  }
}
