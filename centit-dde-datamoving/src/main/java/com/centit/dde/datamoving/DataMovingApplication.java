package com.centit.dde.datamoving;

import com.centit.dde.datamoving.service.TaskRun;
import com.centit.fileserver.common.FileStore;
import com.centit.fileserver.utils.OsFileStore;
import com.centit.product.dataopt.core.BizModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.centit"},
    excludeFilters = @ComponentScan.Filter(value = org.springframework.stereotype.Controller.class))
public class DataMovingApplication  {

    /**
     * 这个只做一个任务的一次交换
     * 由 agent 调度
     * @param args args[0] 为任务ID
     */

    @Value("${framework.app.home}")
    private String appHome;
    @Bean
    public FileStore fileStore() {
        return new OsFileStore(appHome + "/upload");
    }
    public static void main(String[] args) throws SQLException {
        if(args==null || args.length<1){
            return;
        }
        ConfigurableApplicationContext context=SpringApplication.run(DataMovingApplication.class, args);
        TaskRun taskRun =context.getBean(TaskRun.class);
        BizModel bizModel=taskRun.runTask(args[0]);
        System.out.println(bizModel.getModelName());
    }
}
