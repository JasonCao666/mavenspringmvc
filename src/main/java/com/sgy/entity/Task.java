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

    @Column(name = "task_finish_time")
    private String task_finish_time;

    @Column(name = "task_status")
    private String status;

    @ManyToOne
    @JoinColumn(name="project_id")
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

    public String getTask_finish_time() {
        return task_finish_time;
    }

    public void setTask_finish_time(String task_finish_time) {
        this.task_finish_time = task_finish_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
