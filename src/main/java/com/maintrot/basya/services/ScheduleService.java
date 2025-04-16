package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.ScheduleRequest;
import com.maintrot.basya.dtoes.ScheduleResponse;
import com.maintrot.basya.models.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleRequest scheduleRequest);
    ScheduleResponse getSchedule(Long id);
    List<ScheduleResponse> getAllSchedules();
    ScheduleResponse updateSchedule(Long id, ScheduleRequest scheduleRequest);
    void deleteSchedule(Long id);
    List<ScheduleResponse> getSchedulesByMasterName(String masterName);
    List<ScheduleResponse> getSchedulesByMasterPhone(String masterPhone);
    List<ScheduleResponse> getSchedulesByWeekDay(String day);
}
