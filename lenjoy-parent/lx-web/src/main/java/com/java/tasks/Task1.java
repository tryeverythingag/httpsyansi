package com.java.tasks;

import com.java.service.IndexService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.tasks
 * @ClassName: task1
 * @Author: tryev
 * @Description: 动态每隔五分钟扫描商品详情表, 然后找到被修改的数据并且对应的html静态文件
 * @Date: 2020/1/28 12:14
 * @Version: 1.0
 */
@Component
public class Task1 {

    @Autowired
    private IndexService indexService;
    @Autowired
    private Configuration fkConfig;

    //@Scheduled:称之为任务调度,旗下有很多属性
    //fixedRate:按照固定的时间去执行方法
    //cron:
    //@Scheduled(fixedRate = 1000)
    @Scheduled(cron = "1 0/5 * * * *")
    public void test1() throws Exception {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        System.out.println(dateStr);*/
        //从后台数据库获取
        List<Map<String, Object>> productDetailList = indexService.findUpdatedProductDetail();
        //freemaker取出数据后,生成静态的html页面(productId,html)
        //1.获取指定的freemarker模板对象
        File file = null;
        for (Map<String, Object> tempMap : productDetailList) {
            Template template = fkConfig.getTemplate("Product.ftl");
            file = new File("D:\\fremaker\\details\\" + tempMap.get("id") + ".html");
            FileWriter out = new FileWriter(file);
            template.process(tempMap, out);
            out.close();
        }
        System.out.println("从新生成的html文件地址="+file.getPath());
    }
}
