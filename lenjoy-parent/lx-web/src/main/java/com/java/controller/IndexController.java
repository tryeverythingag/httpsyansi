package com.java.controller;

import com.java.service.IndexService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/4- 20:23
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;
    @Autowired
    private Configuration fkConfig;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取横向导航栏
     *
     * @return
     */
    @RequestMapping("/getHxWebMenu.do")
    @ResponseBody
    public List<Map<String, Object>> getHxWebMenu() {
        return indexService.findWebMenus("1");
    }

    //通过注解式来开发redis;
    @RequestMapping("/getZXByRedisZJ.do")
    @ResponseBody
    public List<Map<String, Object>> getZXByRedisZJ() {
        return indexService.findZXMenu();
    }

    //通过注解式来开发redis;
    @RequestMapping("/clear.do")
    @ResponseBody
    public void clear() {
        indexService.clearZxMenu();
    }


    /**
     * 使用fremarker静态化页面
     * 进入商品详情:productId
     *
     * @return
     */
    @RequestMapping("/toProductDetailFTL/{productId}")
    public ModelAndView toProductDetailFTL(@PathVariable(name = "productId") Long productId) {
        //模拟假数据-实际从后台数据库获取
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", "戴尔燃7000");
        dataMap.put("subTitle", "Y7000爆款");
        dataMap.put("price", 5699.00F);
        dataMap.put("type", "经典款");
        dataMap.put("color", "银白色");
        dataMap.put("num", 100);
        //图片
        List<String> imgUrlList = new ArrayList<>();
        imgUrlList.add("http://q3vt543cp.bkt.clouddn.com/2e9b26129bd14fdaaeee572dc154b661.jpg");
        imgUrlList.add("http://q3vt543cp.bkt.clouddn.com/25fb57581e384e0a8cd25bb0b2f35b4c.jpg");
        imgUrlList.add("http://q3vt543cp.bkt.clouddn.com/fd2509768e8849da8b501f2ba61f7fa3.jpg");
        dataMap.put("imgUrlList", imgUrlList);
        //将数据保存到request域中并且跳转到Product--->Product.html
        ModelAndView mv = new ModelAndView("Product");
        mv.addAllObjects(dataMap);
        //mv.addObject("dataMap",dataMap);
        return mv;
    }

    /**
     * 通过freemarker动态生成html文件
     *
     * @return
     */
    @RequestMapping("/doAllStaticProductDetail.do")
    @ResponseBody
    public String doAllStaticProductDetail() throws Exception {
        //从后台数据库获取
        List<Map<String, Object>> productDetailList = indexService.findAllProductDetail();
        //freemaker取出数据后,生成静态的html页面(productId,html)
        //1.获取指定的freemarker模板对象
        File file = null;
        for (Map<String, Object> tempMap : productDetailList) {
            Template template = fkConfig.getTemplate("Product.ftl");
            file = new File("D:\\freemaker\\details\\" + tempMap.get("id") + ".html");
            FileWriter out = new FileWriter(file);
            template.process(tempMap, out);
            out.close();
        }
        return file.getPath();
    }

    /**
     * 配置默认访问首页
     * @return
     */
    @RequestMapping("/")
    public String defaultHomeHTML(){
        return "redirect:/toIndex.do";
    }

    /**
     * 跳转到首页
     * @param model
     * @return
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(Model model){
        //获取商品列表需要实现负载均衡,调用的是consumer.
        List<Map<String, Object>> productDetailList = indexService.findAllProductDetail();
        //封装商品列表
        model.addAttribute("productDetailList",productDetailList);
        //封装轮播图片
        List<Map<String,Object>> bannerList = restTemplate.getForObject("http://lx-web-banner-provider/getWebBanners.do", List.class);
        model.addAttribute("bannerList",bannerList);
        //获取秒杀商品列表
        List<Map<String, Object>> secKillProductList = indexService.findSecKillProductList();
        model.addAttribute("secKillProductList",secKillProductList);
        return "Index";
    }
}