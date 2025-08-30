package org.example.langchan4jdemo.controller;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QwenController {

  @Autowired
  private QwenChatModel qwenChatModel;

  @GetMapping("/testDashScopeQwen")
  public String testDashScopeQwen(
      @RequestParam(value = "message", defaultValue = "Hello") String message) {
    return qwenChatModel.chat(message);
  }

  @GetMapping("/testDashScopeWanx")
  public URI testDashScopeWanx(
      @RequestParam(value = "message", defaultValue = "Hello") String message) {
    WanxImageModel wanxImageModel = WanxImageModel.builder()
        .modelName("wanx2.1-t2i-plus")
        .apiKey("sk-4d55e3cc4c184b61aba53cc51a178d46")
        .build();
    Response<Image> response = wanxImageModel.generate(
        "奇幻森林精灵：在一片弥漫着轻柔薄雾的古老森林深处，阳光透过茂密枝叶洒下金色光斑。一位身材娇小、长着透明薄翼的精灵少女站在一朵硕大的蘑菇上。她有着海藻般的绿色长发，发间点缀着蓝色的小花，皮肤泛着珍珠般的微光。身上穿着由翠绿树叶和白色藤蔓编织而成的连衣裙，手中捧着一颗散发着柔和光芒的水晶球，周围环绕着五彩斑斓的蝴蝶，脚下是铺满苔藓的地面，蘑菇和蕨类植物丛生，营造出神秘而梦幻的氛围。");
    return response.content().url();
  }
}
