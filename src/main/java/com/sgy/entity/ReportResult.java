package com.sgy.entity;

import net.sf.json.JSONArray;

import java.util.List;

public class ReportResult {

    private int taskId;

    private String taskName;

    private String taskDescription;

    private String taskSteps;

    private int successNumber;

    private int participateNumber;

    private int finishInTimeNumber;

    private String incorrectStepsAndNumber;

    private JSONArray incorrects;

    private int efficientNumber;



    public int getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(int successNumber) {
        this.successNumber = successNumber;
    }

    public int getParticipateNumber() {
        return participateNumber;
    }

    public void setParticipateNumber(int participateNumber) {
        this.participateNumber = participateNumber;
    }

    public int getFinishInTimeNumber() {
        return finishInTimeNumber;
    }

    public void setFinishInTimeNumber(int finishInTimeNumber) {
        this.finishInTimeNumber = finishInTimeNumber;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getIncorrectStepsAndNumber() {
        return incorrectStepsAndNumber;
    }

    public void setIncorrectStepsAndNumber(String incorrectStepsAndNumber) {
        this.incorrectStepsAndNumber = incorrectStepsAndNumber;
    }

    public int getEfficientNumber() {
        return efficientNumber;
    }

    public void setEfficientNumber(int efficientNumber) {
        this.efficientNumber = efficientNumber;
    }

    public JSONArray getIncorrects() {
        return incorrects;
    }

    public void setIncorrects(JSONArray incorrects) {
        this.incorrects = incorrects;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskSteps() {
        return taskSteps;
    }

    public void setTaskSteps(String taskSteps) {
        this.taskSteps = taskSteps;
    }
}
