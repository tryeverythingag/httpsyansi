package com.java.controller;

import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2020/1/13- 19:12
 */
@Controller
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 查询后台轮播数据
     * @return
     */
    @RequestMapping("/getBanners.do")
    @ResponseBody
    public List<Map<String,Object>> getBanners(){
        return bannerService.findBanner();
    }

    /**
     * 添加轮播图
     * @param imgUrl
     * @param href
     * @param remark
     * @param sort
     * @return
     */
    @RequestMapping("/addBanner.do")
    @ResponseBody
    public boolean addBanner(String imgUrl,String href,String remark,Integer sort){
        //1.校验数据的正确性
        //2.调用添加轮播信息的业务层
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("imgUrl", imgUrl);
        paramMap.put("href", href);
        paramMap.put("remark", remark);
        paramMap.put("sort", sort);
        return bannerService.saveBanner(paramMap);
    }
}
