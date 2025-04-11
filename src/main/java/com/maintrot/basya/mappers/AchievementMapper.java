package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.AchievementRequest;
import com.maintrot.basya.dtoes.AchievementResponse;
import com.maintrot.basya.models.Achievement;

public interface AchievementMapper {
    AchievementResponse toResponse(Achievement achievement);
    Achievement toEntity(AchievementRequest achievementRequest);
}
