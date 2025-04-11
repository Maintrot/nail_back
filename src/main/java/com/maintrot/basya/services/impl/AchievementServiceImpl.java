package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.AchievementRequest;
import com.maintrot.basya.dtoes.AchievementResponse;
import com.maintrot.basya.mappers.AchievementMapper;
import com.maintrot.basya.models.Achievement;
import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.AchievementRepository;
import com.maintrot.basya.repositories.UserRepository;
import com.maintrot.basya.services.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementMapper achievementMapper;
    private final UserRepository userRepository;

    public AchievementServiceImpl(AchievementMapper achievementMapper, AchievementRepository achievementRepository, UserRepository userRepository) {
        this.achievementMapper = achievementMapper;
        this.achievementRepository = achievementRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AchievementResponse createAchievement(AchievementRequest achievementRequest) {
        User client = userRepository.findById(achievementRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!"USER_CLIENT".equals(client.getRole())) {
            throw new RuntimeException("User is not a client");
        }
        Achievement achievement = achievementMapper.toEntity(achievementRequest);
        achievement.setClient(client);
        Achievement savedAchievement = achievementRepository.save(achievement);
        return achievementMapper.toResponse(savedAchievement);
    }

    @Override
    public AchievementResponse getAchievement(Long id) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));
        return achievementMapper.toResponse(achievement);
    }

    @Override
    public List<AchievementResponse> getAllAchievements() {
        List<Achievement> achievements = achievementRepository.findAll();
        return achievements.stream()
                .map(achievementMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AchievementResponse updateAchievement(Long id, AchievementRequest achievementRequest) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));
        achievement.setName(achievementRequest.getName());
        achievement.setAchievementDescription(achievementRequest.getAchievementDescription());
        achievement.setAchievementProgress(achievementRequest.getAchievementProgress());
        return achievementMapper.toResponse(achievement);
    }

    @Override
    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }
}
