package com.bswork.helper.api.controller;

import com.bswork.helper.api.constants.UriConstants;
import com.bswork.helper.api.mapper.StoryMapper;
import com.bswork.helper.api.model.dto.StoryDto;
import com.bswork.helper.api.model.input.StoryInput;
import com.bswork.helper.api.model.input.StoryInputPartial;
import com.bswork.helper.domain.model.StoryDomain;
import com.bswork.helper.domain.usecase.StoryJourneyUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping(UriConstants.URI_BASE_STORY)
public class StoryController {

    private final StoryMapper storyMapper;
    private final StoryJourneyUsecase storyJourneyUsecase;

    @GetMapping
    public ResponseEntity<List<StoryDto>> getAllStories(@RequestParam(value = "user_id") Long userId,
                                                        @RequestParam(value = "number_story", required = false) String numberStory) {

        List<StoryDomain> storiesDomain = storyJourneyUsecase.getAllStories(userId, numberStory);
        List<StoryDto> storiesDto = storyMapper.toCollectionDto(storiesDomain);

        return ResponseEntity.ok(storiesDto);
    }

    @GetMapping(UriConstants.URI_BASE_STORY_ID)
    public ResponseEntity<StoryDto> getStorieById(@PathVariable Long id) {
        StoryDomain storyDomain = storyJourneyUsecase.getStoryById(id);
        StoryDto storyDto = storyMapper.toDto(storyDomain);

        return ResponseEntity.ok(storyDto);
    }

    @PostMapping
    public ResponseEntity<StoryDto> createStory(@RequestBody @Validated StoryInput storyInput) {
        StoryDomain storyDomainInput = storyMapper.toDomain(storyInput);
        StoryDomain storyDomainCreated = storyJourneyUsecase.createStory(storyDomainInput);
        StoryDto storyDtoCreated = storyMapper.toDto(storyDomainCreated);

        return new ResponseEntity<>(storyDtoCreated, HttpStatus.CREATED);
    }

    @PutMapping(UriConstants.URI_BASE_STORY_ID)
    public ResponseEntity<StoryDto> updateStory(@PathVariable Long id, @RequestBody StoryInputPartial storyInput) {
        StoryDomain storyDomainInput = storyMapper.toDomainPartial(storyInput);
        StoryDomain storyDomainUpdated = storyJourneyUsecase.updateStory(id, storyDomainInput);
        StoryDto storyDtoUpdated = storyMapper.toDto(storyDomainUpdated);

        return ResponseEntity.ok(storyDtoUpdated);
    }

    @DeleteMapping(UriConstants.URI_BASE_STORY_ID)
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {
        storyJourneyUsecase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
