package com.netcracker.avizhen.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Александр on 18.12.2017.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "CURRENT_COURSE_ID")
    private Integer currentCourseId;

    public User() {
    }

    public Integer getId() {
        return id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getCurrentCourseId() {
        return currentCourseId;
    }

    public void setCurrentCourseId(Integer currentCourseId) {
        this.currentCourseId = currentCourseId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", currentCourseId=" + currentCourseId +
                '}';
    }
}
