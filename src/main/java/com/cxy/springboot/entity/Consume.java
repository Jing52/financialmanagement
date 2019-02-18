package com.cxy.springboot.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Consume {
    private Integer consumeId;

    private Integer consumeType;

    private String consumeName;

    private BigDecimal consumeSum;

    private String nation;

    private String provinceId;

    private String cityId;

    private String consumeInfo;

    private Date createTime;

    private String userId;

    private Region region;

    private ConsumeType type;

    public ConsumeType getType() {
        return type;
    }

    public void setType(ConsumeType type) {
        this.type = type;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Integer getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Integer consumeId) {
        this.consumeId = consumeId;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }

    public String getConsumeName() {
        return consumeName;
    }

    public void setConsumeName(String consumeName) {
        this.consumeName = consumeName == null ? null : consumeName.trim();
    }

    public BigDecimal getConsumeSum() {
        return consumeSum;
    }

    public void setConsumeSum(BigDecimal consumeSum) {
        this.consumeSum = consumeSum;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getConsumeInfo() {
        return consumeInfo;
    }

    public void setConsumeInfo(String consumeInfo) {
        this.consumeInfo = consumeInfo == null ? null : consumeInfo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}