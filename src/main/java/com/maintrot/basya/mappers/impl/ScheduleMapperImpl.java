package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.ScheduleRequest;
import com.maintrot.basya.dtoes.ScheduleResponse;
import com.maintrot.basya.enums.WeekDay;
import com.maintrot.basya.mappers.ScheduleMapper;
import com.maintrot.basya.models.Schedule;
import com.maintrot.basya.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public ScheduleResponse toResponse(Schedule schedule) {
        ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setId(scheduleResponse.getId());
        scheduleResponse.setMasterId(schedule.getMaster().getId());
        scheduleResponse.setDay(schedule.getDay().name());
        scheduleResponse.setStartTime(schedule.getStartTime().toString());
        scheduleResponse.setEndTime(schedule.getEndTime().toString());
        return scheduleResponse;
    }

    @Override
    public Schedule toEntity(ScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule();
        User master = new User();
        master.setId(scheduleRequest.getMasterId());
        schedule.setMaster(master);
        schedule.setDay(WeekDay.valueOf(scheduleRequest.getDay()));
        schedule.setStartTime(LocalTime.parse(scheduleRequest.getStartTime()));
        schedule.setEndTime(LocalTime.parse(scheduleRequest.getEndTime()));
        return schedule;
    }
}
