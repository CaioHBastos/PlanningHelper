package com.bswork.helper.dataprovider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Story {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    @Column(name = "story_number")
    private String storyNumber;

    @ManyToOne
    private User user;

    @OneToMany
    @JsonIgnoreProperties(value = {"story"}, allowSetters = true)
    private List<Task> task = new ArrayList<>();
}
