package com.maintrot.basya.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @Where(clause = "role = 'USER_CLIENT'")
    private User client;

    @Column(name = "Ачивка", nullable = false)
    private String name;

    @Column(name = "Описание ачивки", nullable = false)
    private String achievementDescription;

    @Column(name = "прогресс ачивки", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int achievementProgress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
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
