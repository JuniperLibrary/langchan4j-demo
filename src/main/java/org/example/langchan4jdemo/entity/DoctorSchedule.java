package org.example.langchan4jdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSchedule {

  private Long id;
  private String doctorName;
  private String department;
  private String date;
  private String time;  // 上午 / 下午
  private Integer totalSlots;
  private Integer availableSlots;
}
