package com.maintrot.basya.dtoes;

public class AchievementRequest {
    private Long clientId;
    private String name;
    private String achievementDescription;
    private int achievementProgress;

    public AchievementRequest () {}

    public AchievementRequest(String name, Long clientId, String achievementDescription, int achievementProgress) {
        this.name = name;
        this.clientId = clientId;
        this.achievementDescription = achievementDescription;
        this.achievementProgress = achievementProgress;
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
