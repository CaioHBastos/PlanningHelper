package com.bswork.helper.dataprovider.mapper;

import com.bswork.helper.dataprovider.model.Story;
import com.bswork.helper.domain.model.StoryDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class StoryProviderMapper {

    private final ModelMapper modelMapper;

    public Story toEntity(StoryDomain storyDomain) {
        return modelMapper.map(storyDomain, Story.class);
    }

    public StoryDomain toDomain(Story storyCreated) {
        return modelMapper.map(storyCreated, StoryDomain.class);
    }

    public List<StoryDomain> toCollectionDomain(List<Story> stories) {
        return stories.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
