package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.TagRequest;
import com.maintrot.basya.dtoes.TagResponse;

import java.util.List;

public interface TagService {
    TagResponse createTag(TagRequest tagRequest);
    TagResponse getTag(Long id);
    List<TagResponse> getAllTags();
    TagResponse updateTag(Long id, TagRequest tagRequest);
    void deleteTag(Long id);
    List<TagResponse> getTagsByClientName(String clientName);
    List<TagResponse> getTagsByMasterName(String masterName);
}
