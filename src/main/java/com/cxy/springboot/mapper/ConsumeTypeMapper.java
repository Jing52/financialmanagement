package com.cxy.springboot.mapper;

import com.cxy.springboot.entity.ConsumeType;

import java.util.List;

public interface ConsumeTypeMapper {
    List<ConsumeType> selectAllType();
}