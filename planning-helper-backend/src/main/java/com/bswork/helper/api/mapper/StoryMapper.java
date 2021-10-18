package com.bswork.helper.api.mapper;

import com.bswork.helper.api.model.dto.StoryDto;
import com.bswork.helper.api.model.input.StoryInput;
import com.bswork.helper.api.model.input.StoryInputPartial;
import com.bswork.helper.domain.model.StoryDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class StoryMapper {

    private final ModelMapper modelMapper;

    public StoryDomain toDomain(StoryInput storyInput) {
        return modelMapper.map(storyInput, StoryDomain.class);
    }

    public StoryDto toDto(StoryDomain storyDomainCreated) {
        return modelMapper.map(storyDomainCreated, StoryDto.class);
    }

    public List<StoryDto> toCollectionDto(List<StoryDomain> storiesDomain) {
        return storiesDomain.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public StoryDomain toDomainPartial(StoryInputPartial storyInput) {
        return modelMapper.map(storyInput, StoryDomain.class);
    }
}
