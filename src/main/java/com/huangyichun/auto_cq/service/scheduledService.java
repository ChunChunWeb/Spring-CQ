package com.huangyichun.auto_cq.service;


import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.retdata.ApiData;
import net.lz1998.cq.retdata.MessageData;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.*;

import static com.huangyichun.auto_cq.utils.WeatherUtil.getReply;

@Configuration
public class scheduledService {

    /**
     * 需要获取一个coolQ 对象，用于主动发送信息
     */
    static CoolQ mainBot = null;
    static HashMap<Long, List<String>> weatherSubscribes = new HashMap<>();



    static{
        try {
            mainBot = CQGlobal.robots.get(3225088174L);
        } catch (Exception e) {
            System.out.println("加载主类mainBot错误");
        }
        // 仅仅测试使用
        LinkedList<String> cities = new LinkedList<>();
        cities.add("成都");
        weatherSubscribes.put(499154897L, cities);

    }

    /**
     * 每天向订阅用户推送信息 需要一个coolQ
     */
    @Scheduled(cron = "0 0 7 * * ?")
    public void weather() {
        if (!isGotMainBot()) {
            return;
        }
        for (Map.Entry<Long, List<String>> next : weatherSubscribes.entrySet()) {
            Long user_id = next.getKey();
            for (String s : next.getValue()) {
                mainBot.sendPrivateMsg(user_id, getReply(s), false);
            }
        }

    }



    /**
     * 在主动推送消息时，必须使用该方法来调用，防止离线
     * todo 需要判断主QQ掉线情况
     * @return 是否获得成功加载主QQ
     */
    public boolean isGotMainBot() {
        if (mainBot == null) {
            try {
                mainBot = CQGlobal.robots.get(3225088174L);
            } catch (Exception e) {
                System.out.println("加载主类mainBot错误");
                return false;
            }
        }
        return true;
    }

    /**
     * 参考 https://blog.csdn.net/kzcming/article/details/102390593
     * 错误原因 springboot + quartz + websocket (spring 自带得websocket 模块) 引起的冲突问题
     * 需要添加一个自己的TaskScheduler调度，不然会使用websocket自带的调度
     * @return 注册的TaskSchedule
     */
    @Bean
    @Nullable
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolScheduler = new ThreadPoolTaskScheduler();
        threadPoolScheduler.setThreadNamePrefix("SockJS-");
        threadPoolScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
        threadPoolScheduler.setRemoveOnCancelPolicy(true);
        return threadPoolScheduler;
    }
}
