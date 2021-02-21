package com.java.service.impl;

import com.java.mapper.BannerMapper;
import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/16- 16:01
 */
@Service
@Transactional(readOnly = false)
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Map<String,Object>> findWebBanner(){
        return bannerMapper.getWebBanner();
    }

}
