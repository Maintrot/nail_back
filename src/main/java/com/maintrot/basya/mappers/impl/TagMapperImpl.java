package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.TagRequest;
import com.maintrot.basya.dtoes.TagResponse;
import com.maintrot.basya.mappers.TagMapper;
import com.maintrot.basya.models.Tag;
import com.maintrot.basya.models.User;
import org.springframework.stereotype.Component;

@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagResponse toResponse(Tag tag) {
        TagResponse tagResponse = new TagResponse();
        tagResponse.setId(tag.getId());
        tagResponse.setClientId(tag.getClient().getId());
        tagResponse.setMasterId(tag.getMaster().getId());
        tagResponse.setText(tag.getText());
        return tagResponse;
    }

    @Override
    public Tag toEntity(TagRequest tagRequest) {
        Tag tag = new Tag();
        User client = new User();
        User master = new User();
        client.setId(tagRequest.getClientId());
        master.setId(tagRequest.getMasterId());
        tag.setClient(client);
        tag.setMaster(master);
        tag.setText(tagRequest.getText());
        return tag;
    }
}
