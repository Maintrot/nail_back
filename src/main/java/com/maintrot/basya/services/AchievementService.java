package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.AchievementRequest;
import com.maintrot.basya.dtoes.AchievementResponse;

import java.util.List;

public interface AchievementService {
    AchievementResponse createAchievement(AchievementRequest achievementRequest);
    AchievementResponse getAchievement(Long id);
    List<AchievementResponse> getAllAchievements();
    AchievementResponse updateAchievement(Long id, AchievementRequest achievementRequest);
    void deleteAchievement(Long id);
}
