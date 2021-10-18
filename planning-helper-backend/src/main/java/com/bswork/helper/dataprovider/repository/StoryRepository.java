package com.bswork.helper.dataprovider.repository;

import com.bswork.helper.dataprovider.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findAllByUserId(Long id);
    List<Story> findByStoryNumber(String storyNumber);
}
