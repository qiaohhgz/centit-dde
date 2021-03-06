package com.centit.dde.agent;

import com.centit.dde.agent.service.ContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.centit"},
    excludeFilters = @ComponentScan.Filter(value = org.springframework.stereotype.Controller.class))
public class AgentApplication {
    /**
     * 运行一个后台进程，最好作为服务启动
     * @param args 为 任务分组号
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(AgentApplication.class, args);
        ContextUtils.setApplicationContext(context);
        // 运行一个 定时程序
        // 修改为基于spring boot的定时程序
        // 系统要定时的刷新 定时任务清单
        // D_EXCHANGE_TASK 根据这个表信息 刷新 定时任务
        System.out.println(args);
    }
}
