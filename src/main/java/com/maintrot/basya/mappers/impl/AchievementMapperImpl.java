package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.AchievementRequest;
import com.maintrot.basya.dtoes.AchievementResponse;
import com.maintrot.basya.mappers.AchievementMapper;
import com.maintrot.basya.models.Achievement;
import com.maintrot.basya.models.User;
import org.springframework.stereotype.Component;

@Component
public class AchievementMapperImpl implements AchievementMapper {

    @Override
    public AchievementResponse toResponse(Achievement achievement) {
        AchievementResponse achievementResponse = new AchievementResponse();
        achievementResponse.setId(achievement.getId());
        achievementResponse.setClientId(achievement.getClient().getId());
        achievementResponse.setName(achievement.getName());
        achievementResponse.setAchievementDescription(achievement.getAchievementDescription());
        achievementResponse.setAchievementProgress(achievement.getAchievementProgress());
        return achievementResponse;
    }

    @Override
    public Achievement toEntity(AchievementRequest achievementRequest) {
        Achievement achievement = new Achievement();
        User client = new User();
        client.setId(achievementRequest.getClientId());
        achievement.setClient(client);
        achievement.setName(achievementRequest.getName());
        achievement.setAchievementDescription(achievementRequest.getAchievementDescription());
        achievement.setAchievementProgress(achievementRequest.getAchievementProgress());
        return achievement;
    }
}
