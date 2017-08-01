package net.tf.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Unit {

    private Long serialNumber;
    private LocalDateTime operationDate;
    private String operationPlace;
    private String operationName;

    @Id
    private Long stateHash;
    private Long parentId;

    @Transient
    private String producePlace;
    @Transient
    private LocalDateTime produceDate;

    public Unit() {
    }



    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationPlace() {
        return operationPlace;
    }

    public void setOperationPlace(String operationPlace) {
        this.operationPlace = operationPlace;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Long getStateHash() {
        return stateHash;
    }

    public void setStateHash(Long stateHash) {
        this.stateHash = stateHash;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getProducePlace() {
        return producePlace;
    }

    public void setProducePlace(String producePlace) {
        this.producePlace = producePlace;
    }

    public LocalDateTime getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(LocalDateTime produceDate) {
        this.produceDate = produceDate;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "serialNumber=" + serialNumber +
                ", operationDate=" + operationDate +
                ", operationPlace='" + operationPlace + '\'' +
                ", operationName='" + operationName + '\'' +
                ", stateHash=" + stateHash +
                ", parentId=" + parentId +
                ", producePlace='" + producePlace + '\'' +
                ", produceDate=" + produceDate +
                '}';
    }
}
