package com.netcracker.avizhen.persistence.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by Александр on 18.12.2017.
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    @Type(type = "date")
    private Date startDate;

    @Column(name = "end_date")
    @Type(type = "date")
    private Date endDate;

    @Column(name = "group_state_id")
    private Integer groupStateId;

    public Group() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getGroupStateId() {
        return groupStateId;
    }

    public void setGroupStateId(Integer groupStateId) {
        this.groupStateId = groupStateId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", groupStateId=" + groupStateId +
                '}';
    }
}
