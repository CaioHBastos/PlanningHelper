package com.bswork.helper.domain.gateway;

import com.bswork.helper.domain.model.StoryDomain;

import java.util.List;

public interface StoryGateway {

    StoryDomain createStory(StoryDomain storyDomain);
    List<StoryDomain> getAllStoriesByUserId(Long userId);
    StoryDomain getStoryById(Long id);
    List<StoryDomain> getStoryByNumber(String numberStory);
    void deleteById(Long id);
}
