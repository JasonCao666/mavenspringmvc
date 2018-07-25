package com.sgy.entity;

import javax.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "Task")
public class Task{

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_description")
    private String description;

    @Column(name = "task_plan_time")
    private String task_plan_time;

    @Column(name = "task_efficient_step")
    private String task_efficient_step;

    @Column(name = "task_end_step")
    private String task_end_step;

    @ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE })
    private Project project ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTask_plan_time() {
        return task_plan_time;
    }

    public void setTask_plan_time(String task_plan_time) {
        this.task_plan_time = task_plan_time;
    }

    public String getTask_efficient_step() {
        return task_efficient_step;
    }

    public void setTask_efficient_step(String task_efficient_step) {
        this.task_efficient_step = task_efficient_step;
    }

    public String getTask_end_step() {
        return task_end_step;
    }

    public void setTask_end_step(String task_end_step) {
        this.task_end_step = task_end_step;
    }
}
