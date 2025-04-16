package com.maintrot.basya.repositories;

import com.maintrot.basya.enums.WeekDay;
import com.maintrot.basya.models.Schedule;
import com.maintrot.basya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMaster(User master);
    List<Schedule> findByDay(WeekDay day);
}
