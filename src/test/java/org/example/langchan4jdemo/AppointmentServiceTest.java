package org.example.langchan4jdemo;

import org.example.langchan4jdemo.entity.Appointment;
import org.example.langchan4jdemo.service.IAppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppointmentServiceTest {

  @Autowired
  private IAppointmentService appointmentService;

  @Test
  void testGetOne() {
    Appointment appointment = new Appointment();
    appointment.setUsername("张三");
    appointment.setIdCard("123456789012345678");
    appointment.setDepartment("内科");
    appointment.setDate("2025-04-14");
    appointment.setTime("上午");

    Appointment appointmentDB = appointmentService.getOne(appointment);
    System.out.println(appointmentDB);
  }

  @Test
  void testSave() {
    Appointment appointment = new Appointment();
    appointment.setUsername("张三");
    appointment.setIdCard("123456789012345678");
    appointment.setDepartment("内科");
    appointment.setDate("2025-04-14");
    appointment.setTime("上午");
    appointment.setDoctorName("张医生");

    appointmentService.save(appointment);
  }

  @Test
  void testRemoveById() {
    appointmentService.removeById(1L);
  }
}
