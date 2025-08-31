package org.example.langchan4jdemo.tools;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import java.util.List;
import org.example.langchan4jdemo.entity.Appointment;
import org.example.langchan4jdemo.entity.DoctorSchedule;
import org.example.langchan4jdemo.mapper.AppointmentMapper;
import org.example.langchan4jdemo.mapper.DoctorScheduleMapper;
import org.example.langchan4jdemo.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {

  @Autowired
  private IAppointmentService appointmentService;

  @Autowired
  private AppointmentMapper appointmentMapper;

  @Autowired
  private DoctorScheduleMapper doctorScheduleMapper;


  @Tool(name = "预约挂号", value = "先检查医生排班是否有剩余号源，再判断用户是否重复预约，成功则保存预约并扣减号源")
  public String bookAppointment(Appointment appointment) {
    // 1. 校验排班
    LambdaQueryWrapper<DoctorSchedule> scheduleWrapper = new LambdaQueryWrapper<>();
    scheduleWrapper.eq(DoctorSchedule::getDepartment, appointment.getDepartment())
        .eq(DoctorSchedule::getDate, appointment.getDate())
        .eq(DoctorSchedule::getTime, appointment.getTime())
        .eq(DoctorSchedule::getDoctorName, appointment.getDoctorName());

    DoctorSchedule schedule = doctorScheduleMapper.selectOne(scheduleWrapper);
    if (schedule == null) {
      return "该医生当天没有排班，无法预约";
    }
    if (schedule.getAvailableSlots() <= 0) {
      return "号源已满，请选择其他时间或医生";
    }

    // 2. 校验用户是否已有相同预约
    LambdaQueryWrapper<Appointment> existWrapper = new LambdaQueryWrapper<>();
    existWrapper.eq(Appointment::getIdCard, appointment.getIdCard())
        .eq(Appointment::getDepartment, appointment.getDepartment())
        .eq(Appointment::getDate, appointment.getDate())
        .eq(Appointment::getTime, appointment.getTime())
        .eq(Appointment::getDoctorName, appointment.getDoctorName())
        .eq(Appointment::getStatus, "已预约");

    Appointment exist = appointmentMapper.selectOne(existWrapper);
    if (exist != null) {
      return "您已预约了该医生在相同时间的号源，请勿重复预约";
    }

    // 3. 保存预约 + 扣减号源
    appointment.setId(null);
    appointment.setStatus("已预约");
    boolean saved = appointmentService.save(appointment);
    if (saved) {
      schedule.setAvailableSlots(schedule.getAvailableSlots() - 1);
      doctorScheduleMapper.updateById(schedule);
      return "预约成功，剩余号源：" + schedule.getAvailableSlots();
    }
    return "预约失败，请稍后再试";
  }


//  @Tool(name = "取消预约挂号", value = "根据参数，查询预约是否存在，如果存在则删除预约记录并返回取消预约成功，否则返回取消预约失败")
//  public String cancelAppointment(Appointment appointment) {
//
//    Appointment appointmentDB = appointmentService.getOne(appointment);
//    if (appointmentDB != null) {
//      //删除预约记录
//      if (appointmentService.removeById(appointmentDB.getId())) {
//        return "取消预约成功";
//      } else {
//        return "取消预约失败";
//      }
//    }
//
//    //取消失败
//    return "您没有预约记录，请核对预约科室和时间";
//  }

  @Tool(name = "取消预约挂号", value = "查询预约是否存在，如果存在则更新状态为已取消，并恢复医生号源")
  public String cancelAppointment(Appointment appointment) {
    LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard())
        .eq(Appointment::getDepartment, appointment.getDepartment())
        .eq(Appointment::getDate, appointment.getDate())
        .eq(Appointment::getTime, appointment.getTime())
        .eq(Appointment::getDoctorName, appointment.getDoctorName())
        .eq(Appointment::getStatus, "已预约");

    Appointment appointmentDB = appointmentMapper.selectOne(queryWrapper);
    if (appointmentDB == null) {
      return "您没有该时间段的预约记录";
    }

    // 1. 修改预约状态为已取消
    appointmentDB.setStatus("已取消");
    appointmentService.updateById(appointmentDB);

    // 2. 恢复医生号源
    LambdaQueryWrapper<DoctorSchedule> scheduleWrapper = new LambdaQueryWrapper<>();
    scheduleWrapper.eq(DoctorSchedule::getDepartment, appointmentDB.getDepartment())
        .eq(DoctorSchedule::getDate, appointmentDB.getDate())
        .eq(DoctorSchedule::getTime, appointmentDB.getTime())
        .eq(DoctorSchedule::getDoctorName, appointmentDB.getDoctorName());

    DoctorSchedule schedule = doctorScheduleMapper.selectOne(scheduleWrapper);
    if (schedule != null) {
      schedule.setAvailableSlots(schedule.getAvailableSlots() + 1);
      doctorScheduleMapper.updateById(schedule);
    }

    return "取消预约成功";
  }



//  @Tool(name = "查询是否有号源", value = "根据科室名称，日期，时间和医生查询是否有号源，并返回给用户")
//  public boolean queryDepartment(
//      @P(value = "科室名称") String name,
//      @P(value = "日期",required = false) String date,
//      @P(value = "时间，可选值：上午、下午",required = false) String time,
//      @P(value = "医生名称", required = true) String doctorName
//  ) {
//
//    System.out.println("查询是否有号源");
//    System.out.println("科室名称：" + name);
//    System.out.println("日期：" + date);
//    System.out.println("时间：" + time);
//    System.out.println("医生名称：" + doctorName);
//
//    //TODO 维护医生的排班信息：
//    //如果没有指定医生名字，则根据其他条件查询是否有可以预约的医生（有返回true，否则返回false）；
//
//    LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
//    queryWrapper.eq(Appointment::getDepartment,name);
//    queryWrapper.eq(Appointment::getDoctorName,doctorName);
//    List<Appointment> appointments = appointmentMapper.selectList(queryWrapper);
//    //如果指定了医生名字，则判断医生是否有排班（没有排版返回false）
//    //如果有排班，则判断医生排班时间段是否已约满（约满返回false，有空闲时间返回true）
//    return !appointments.isEmpty();
//  }

  @Tool(name = "查询是否有号源",
      value = "根据科室、日期、时间、医生查询是否有剩余号源，返回剩余数量和可选医生列表")
  public String queryDepartment(
      @P(value = "科室名称") String department,
      @P(value = "日期", required = true) String date,
      @P(value = "时间，可选值：上午、下午", required = true) String time,
      @P(value = "医生名称", required = false) String doctorName
  ) {
    LambdaQueryWrapper<DoctorSchedule> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(DoctorSchedule::getDepartment, department)
        .eq(DoctorSchedule::getDate, date)
        .eq(DoctorSchedule::getTime, time);

    if (doctorName != null && !doctorName.isEmpty()) {
      wrapper.eq(DoctorSchedule::getDoctorName, doctorName);
    }

    List<DoctorSchedule> schedules = doctorScheduleMapper.selectList(wrapper);
    if (schedules.isEmpty()) {
      return "没有排班信息，无法预约";
    }

    StringBuilder sb = new StringBuilder();
    if (doctorName != null && !doctorName.isEmpty()) {
      // 查询单个医生
      DoctorSchedule schedule = schedules.get(0);
      if (schedule.getAvailableSlots() > 0) {
        sb.append("医生【").append(schedule.getDoctorName()).append("】")
            .append("在").append(date).append("的").append(time)
            .append("有剩余号源：").append(schedule.getAvailableSlots()).append(" 个");
      } else {
        sb.append("医生【").append(schedule.getDoctorName()).append("】")
            .append("在").append(date).append("的").append(time)
            .append("号源已满");
      }
    } else {
      // 返回该科室所有医生号源情况
      sb.append("科室【").append(department).append("】在 ").append(date).append(" 的 ").append(time).append(" 号源情况：\n");
      for (DoctorSchedule schedule : schedules) {
        sb.append("医生【").append(schedule.getDoctorName()).append("】")
            .append("：剩余 ").append(schedule.getAvailableSlots()).append(" 个\n");
      }
    }

    return sb.toString();
  }
}
