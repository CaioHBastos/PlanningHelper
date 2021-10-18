package com.bswork.helper.domain.usecase;

import com.bswork.helper.domain.exception.NoContentStoryException;
import com.bswork.helper.domain.gateway.StoryGateway;
import com.bswork.helper.domain.gateway.TaskGateway;
import com.bswork.helper.domain.model.StoryDomain;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StoryJourneyUsecase {

    private final StoryGateway storyGateway;
    private final TaskGateway taskGateway;
    private final UserJourneyUsecase userJourneyUsecase;

    public StoryDomain createStory(StoryDomain storyDomainInput) {
        userJourneyUsecase.getByIdUser(storyDomainInput.getUser().getId());
        return storyGateway.createStory(storyDomainInput);
    }

    public StoryDomain getStoryById(Long id) {
        return storyGateway.getStoryById(id);
    }

    public List<StoryDomain> getAllStories(Long userId, String numberStory) {

        if (StringUtils.isNotBlank(numberStory)) {
            List<StoryDomain> storiesDomain = storyGateway.getStoryByNumber(numberStory);
            verifyListEmpty(storiesDomain);
            return storiesDomain;
        }

        userJourneyUsecase.getByIdUser(userId);
        List<StoryDomain> storiesDomain = storyGateway.getAllStoriesByUserId(userId);
        verifyListEmpty(storiesDomain);
        return storiesDomain;
    }

    private void verifyListEmpty(List<StoryDomain> storiesDomain) {
        if (storiesDomain.isEmpty()) {
            throw new NoContentStoryException();
        }
    }

    public void deleteById(Long id) {
        StoryDomain storyDomain = getStoryById(id);
        taskGateway.deleteAllByStoryNumber(storyDomain.getStoryNumber());
        storyGateway.deleteById(id);
    }

    public StoryDomain updateStory(Long id, StoryDomain storyDomainInput) {
        StoryDomain storyDomainActual = getStoryById(id);

        if (StringUtils.isNotBlank(storyDomainInput.getTitle())) {
            storyDomainActual.setTitle(storyDomainInput.getTitle());
        }

        if (StringUtils.isNotBlank(storyDomainInput.getStoryNumber())) {
            storyDomainActual.setStoryNumber(storyDomainInput.getStoryNumber());
        }

        return storyGateway.createStory(storyDomainActual);
    }
}
