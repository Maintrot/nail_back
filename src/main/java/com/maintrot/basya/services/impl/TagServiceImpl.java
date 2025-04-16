package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.TagRequest;
import com.maintrot.basya.dtoes.TagResponse;
import com.maintrot.basya.enums.Role;
import com.maintrot.basya.mappers.TagMapper;
import com.maintrot.basya.models.Tag;
import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.TagRepository;
import com.maintrot.basya.repositories.UserRepository;
import com.maintrot.basya.services.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public TagServiceImpl(TagMapper tagMapper, TagRepository tagRepository, UserRepository userRepository) {
        this.tagMapper = tagMapper;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TagResponse createTag(TagRequest tagRequest) {
        User client = userRepository.findById(tagRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!Role.USER_CLIENT.equals(client.getRole())) {
            throw new RuntimeException("User is not a client");
        }
        User master = userRepository.findById(tagRequest.getMasterId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!Role.USER_MASTER.equals(master.getRole())) {
            throw new RuntimeException("User is not a master");
        }
        Tag tag = tagMapper.toEntity(tagRequest);
        tag.setClient(client);
        tag.setMaster(master);
        Tag savedTag = tagRepository.save(tag);
        return tagMapper.toResponse(savedTag);
    }

    @Override
    public TagResponse getTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return tagMapper.toResponse(tag);
    }

    @Override
    public List<TagResponse> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(tagMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TagResponse updateTag(Long id, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setText(tagRequest.getText());
        return tagMapper.toResponse(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<TagResponse> getTagsByClientName(String clientName) {
        User client = userRepository.findByName(clientName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!Role.USER_CLIENT.equals(client.getRole())) {
            throw new RuntimeException("User is not a client");
        }
        List<Tag> tags = tagRepository.findByClient(client);
        return tags.stream()
                .map(tagMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TagResponse> getTagsByMasterName(String masterName) {
        User master = userRepository.findByName(masterName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!Role.USER_MASTER.equals(master.getRole())) {
            throw new RuntimeException("User is not a master");
        }
        List<Tag> tags = tagRepository.findByMaster(master);
        return tags.stream()
                .map(tagMapper::toResponse)
                .collect(Collectors.toList());
    }
}
