package com.cxy.springboot.service;

import com.cxy.springboot.entity.ConsumeType;
import com.cxy.springboot.entity.Region;
import com.cxy.springboot.mapper.ConsumeTypeMapper;
import com.cxy.springboot.mapper.RegionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cxy
 * @Date: 2019/1/15
 * @Description:
 */
@Service
public class ComboboxService {
    private final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private ConsumeTypeMapper consumeTypeMapper;

    @Autowired
    private RegionMapper regionMapper;

    /**
     * 消费类型
     * @return
     */
    public List<ConsumeType> selectAllType() {
        return consumeTypeMapper.selectAllType();
    }

    /**
     * 区域
     * @param area
     * @return
     */
    public List<Region> selectAllRegion(String area){
        List<Region> list = null;
        try{
            logger.info("获取区域入参："+ area);
            list = regionMapper.selectAllRegion(area);
            logger.info("获取区域出参："+ list.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}