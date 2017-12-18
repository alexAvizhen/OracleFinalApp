package com.netcracker.avizhen.persistence.utils;

/**
 * Created by Александр on 18.12.2017.
 */
public class CurrentPresentationTable<T> {
    private Class<T> currentPresentationEntity;

    public CurrentPresentationTable() {
        currentPresentationEntity = null;
    }

    public Class<T> getCurrentPresentationEntity() {
        return currentPresentationEntity;
    }

    public void setCurrentPresentationEntity(Class<T> currentPresentationEntity) {
        this.currentPresentationEntity = currentPresentationEntity;
    }
}
