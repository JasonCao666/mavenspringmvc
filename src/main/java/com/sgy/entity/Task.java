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
}
