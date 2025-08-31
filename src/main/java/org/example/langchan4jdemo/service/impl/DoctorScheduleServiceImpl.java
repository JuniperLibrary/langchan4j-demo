package org.example.langchan4jdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.langchan4jdemo.entity.DoctorSchedule;
import org.example.langchan4jdemo.mapper.DoctorScheduleMapper;
import org.example.langchan4jdemo.service.IDoctorScheduleService;
import org.springframework.stereotype.Service;

@Service
public class DoctorScheduleServiceImpl extends
    ServiceImpl<DoctorScheduleMapper, DoctorSchedule> implements IDoctorScheduleService {

}
