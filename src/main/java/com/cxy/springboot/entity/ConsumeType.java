package com.cxy.springboot.entity;

public class ConsumeType {
    private Integer rankId;

    private Integer consumeCode;

    private String typeName;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Integer getConsumeCode() {
        return consumeCode;
    }

    public void setConsumeCode(Integer consumeCode) {
        this.consumeCode = consumeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}