package org.example.langchan4jdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.langchan4jdemo.entity.Appointment;

public interface IAppointmentService extends IService<Appointment> {
  Appointment getOne(Appointment appointment);
}
