package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.ScheduleRequest;
import com.maintrot.basya.dtoes.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleRequest scheduleRequest);
    ScheduleResponse getSchedule(Long id);
    List<ScheduleResponse> getAllSchedules();
    ScheduleResponse updateSchedule(Long id, ScheduleRequest scheduleRequest);
    void deleteSchedule(Long id);
}
