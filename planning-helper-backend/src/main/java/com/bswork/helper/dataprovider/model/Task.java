package com.bswork.helper.dataprovider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Task {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "issue_type")
    private String issueType;

    private String description;

    private String summary;

    private Long hours;

    @Column(name = "issue_id")
    private Integer issueId;

    @Column(name = "epic_link")
    private String epicLink;

    @Column(name = "complexity_points")
    private String complexityPoints;

    private String priority;

    private String components;

    @Column(name = "fix_versions")
    private String fixVersions;

    private String labels;

    @Column(name = "due_date")
    private String dueDate;

    private String team;

    @Column(name = "original_estimate")
    private Long originalEstimate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks" }, allowSetters = true)
    private Story story;
}
