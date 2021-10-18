package com.bswork.helper.dataprovider.repository;

import com.bswork.helper.dataprovider.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByStory_storyNumber(String jiraKey);
    List<Task> findAllByStory_UserId(Long userId);
    void deleteAllByStory_storyNumber(String storyNumber);
}
