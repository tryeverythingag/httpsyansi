package com.java.tasks;

import com.java.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: lenjoy-parent
 * @Package: com.java.tasks
 * @ClassName: Task2
 * @Author: tryev
 * @Description: 秒杀
 * @Date: 2020/1/30 19:33
 * @Version: 1.0
 */
@Component
public class Task2 {

    @Autowired
    private IndexService indexService;

    /**
     * 扫描开始秒杀的产品,将秒杀的产品保存到redis中去,并且再数据库中更改为开始秒杀.
     */
    @Scheduled(cron="0/5 * * * * *")
    public void doSecKillStep1(){
        indexService.processSecKill();
    }

    /**
     * 秒杀结束,数据库更改为2
     */
    @Scheduled(cron="0/5 * * * * *")
    public void updateEnd() {
        indexService.updateEnd();
    }

}
