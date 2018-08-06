package com.sgy.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Report")
public class Report {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "report_time")
    private String reportTime;

    @Column(name = "report_steps", length = 65536)
    private String reportSteps;

    @ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE })
    @JoinColumn(name = "project_id")
    private Project project ;

    @Transient
    private List<String> steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportSteps() {
        return reportSteps;
    }

    public void setReportSteps(String reportSteps) {
        this.reportSteps = reportSteps;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
