package com.bswork.helper.dataprovider.service;

import com.bswork.helper.dataprovider.exception.StoryNotFoundException;
import com.bswork.helper.dataprovider.mapper.StoryProviderMapper;
import com.bswork.helper.dataprovider.model.Story;
import com.bswork.helper.dataprovider.repository.StoryRepository;
import com.bswork.helper.domain.gateway.StoryGateway;
import com.bswork.helper.domain.model.StoryDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Component
public class StoryProviderService implements StoryGateway {

    private final StoryProviderMapper storyProviderMapper;
    private final StoryRepository storyRepository;

    @Override
    public List<StoryDomain> getAllStoriesByUserId(Long userId) {
        List<Story> stories = storyRepository.findAllByUserId(userId);
        return storyProviderMapper.toCollectionDomain(stories);
    }

    @Override
    public StoryDomain getStoryById(Long id) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new StoryNotFoundException(
                        String.format("O id da história informada '%s' não existe", id)));

        return storyProviderMapper.toDomain(story);
    }

    @Override
    public List<StoryDomain> getStoryByNumber(String numberStory) {
        List<Story> story = storyRepository.findByStoryNumber(numberStory);
        return storyProviderMapper.toCollectionDomain(story);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        storyRepository.deleteById(id);
    }

    @Transactional
    @Override
    public StoryDomain createStory(StoryDomain storyDomain) {
        Story story = storyProviderMapper.toEntity(storyDomain);
        Story storyCreated = storyRepository.save(story);

        return storyProviderMapper.toDomain(storyCreated);
    }
}
