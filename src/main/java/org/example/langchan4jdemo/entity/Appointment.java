package org.example.langchan4jdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

  private Long id;
  private String username;
  private String idCard;
  private String department;
  private String date;
  private String time;       // 上午 / 下午
  private String doctorName;
  private String status;     // 已预约、已取消、已完成
}

