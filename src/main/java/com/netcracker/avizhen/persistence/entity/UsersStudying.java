package com.netcracker.avizhen.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Александр on 18.12.2017.
 */
@Entity
@Table(name = "users_studying")
public class UsersStudying {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_passed")
    private String isPassed;

    @Column(name = "is_available")
    private String isAvailable;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "program_place_id")
    private Integer programPlaceId;

    public UsersStudying() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(String isPassed) {
        this.isPassed = isPassed;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProgramPlaceId() {
        return programPlaceId;
    }

    public void setProgramPlaceId(Integer programPlaceId) {
        this.programPlaceId = programPlaceId;
    }

    @Override
    public String toString() {
        return "UsersStudying{" +
                "id=" + id +
                ", isPassed='" + isPassed + '\'' +
                ", isAvailable='" + isAvailable + '\'' +
                ", programId=" + programId +
                ", userId=" + userId +
                ", programPlaceId=" + programPlaceId +
                '}';
    }
}
