package com.maintrot.basya.dtoes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AchievementResponse {
    private Long id;
    private Long clientId;
    private String name;
    private String achievementDescription;
    private int achievementProgress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        this.achievementDescription = achievementDescription;
    }

    public int getAchievementProgress() {
        return achievementProgress;
    }

    public void setAchievementProgress(int achievementProgress) {
        this.achievementProgress = achievementProgress;
    }
}
