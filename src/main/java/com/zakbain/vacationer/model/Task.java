package com.zakbain.vacationer.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column (name = "completed_on")
    private Date completedOn;

    @Column (name = "project_id")
    private Integer projectId;
    public Integer getId() {
        return id;
    }

    public Task() {}

    public Task(Integer id, String name, String description, Date completedOn, Integer projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completedOn = completedOn;
        this.projectId = projectId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
