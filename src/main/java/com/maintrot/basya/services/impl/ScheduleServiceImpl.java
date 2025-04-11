package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.ScheduleRequest;
import com.maintrot.basya.dtoes.ScheduleResponse;
import com.maintrot.basya.enums.WeekDay;
import com.maintrot.basya.mappers.ScheduleMapper;
import com.maintrot.basya.models.Schedule;
import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.ScheduleRepository;
import com.maintrot.basya.repositories.UserRepository;
import com.maintrot.basya.services.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ScheduleResponse createSchedule(ScheduleRequest scheduleRequest) {
        User master = userRepository.findById(scheduleRequest.getMasterId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!"USER_MASTER".equals(master.getRole())) {
            throw new RuntimeException("User is not a master");
        }
        Schedule schedule = scheduleMapper.toEntity(scheduleRequest);
        schedule.setMaster(master);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toResponse(savedSchedule);
    }

    @Override
    public ScheduleResponse getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return scheduleMapper.toResponse(schedule);
    }

    @Override
    public List<ScheduleResponse> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(scheduleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponse updateSchedule(Long id, ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        if (scheduleRequest.getMasterId() != null) {
            // Обновляем только идентификатор мастера (при необходимости)
            schedule.getMaster().setId(scheduleRequest.getMasterId());
        }
        if (scheduleRequest.getDay() != null) {
            schedule.setDay(WeekDay.valueOf(scheduleRequest.getDay()));
        }
        if (scheduleRequest.getStartTime() != null) {
            schedule.setStartTime(java.time.LocalTime.parse(scheduleRequest.getStartTime()));
        }
        if (scheduleRequest.getEndTime() != null) {
            schedule.setEndTime(java.time.LocalTime.parse(scheduleRequest.getEndTime()));
        }
        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toResponse(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
