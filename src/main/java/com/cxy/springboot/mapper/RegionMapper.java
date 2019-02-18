package com.cxy.springboot.mapper;

import com.cxy.springboot.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RegionMapper {
    /**
     * 区域
     * @param area
     * @return
     */
    List<Region> selectAllRegion(String area);
}