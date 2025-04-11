package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.ScheduleRequest;
import com.maintrot.basya.dtoes.ScheduleResponse;
import com.maintrot.basya.models.Schedule;

public interface ScheduleMapper {
    ScheduleResponse toResponse(Schedule schedule);
    Schedule toEntity(ScheduleRequest scheduleRequest);
}
