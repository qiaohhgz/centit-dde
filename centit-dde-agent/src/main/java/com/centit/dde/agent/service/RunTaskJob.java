package com.centit.dde.agent.service;

import com.centit.dde.dao.TaskLogDao;
import com.centit.dde.po.TaskExchange;
import com.centit.dde.po.TaskLog;
import com.centit.support.quartz.AbstractQuartzJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.util.Date;


public class RunTaskJob extends AbstractQuartzJob {
    private TaskExchange taskExchange;
    public RunTaskJob() {
    }

    @Override
    protected void loadExecutionContext(JobExecutionContext context) {
        JobDataMap paramMap = context.getMergedJobDataMap();
        this.taskExchange = (TaskExchange)paramMap.get("taskExchange");
    }

    @Override
    protected boolean runRealJob(JobExecutionContext context) throws JobExecutionException {
        //System.out.println(this.taskExchange.getTaskId());

//TODO 生成logid,使用command调用datamoving jar包
        TaskLogDao taskLogDao =ContextUtils.getBean(TaskLogDao.class);
        TaskLog taskLog = new TaskLog();
        taskLog.setTaskId(this.taskExchange.getTaskId());
        taskLog.setRunBeginTime(new Date());
        taskLog.setRunType(this.taskExchange.getTaskName());
        taskLogDao.saveNewObject(taskLog);
        PathConfig pathConfig = ContextUtils.getBean(PathConfig.class);
        try {
            Process p = Runtime.getRuntime().exec("java -jar "+pathConfig.getDataMovingPath()+" "+taskLog.getLogId());
//            //取得命令结果的输出流
//            InputStream fis=p.getInputStream();
//            //用一个读输出流类去读
//            InputStreamReader isr=new InputStreamReader(fis);
//            //用缓冲器读行
//            BufferedReader br=new BufferedReader(isr);
//            String line=null;
//            //直到读完为止
//            while((line=br.readLine())!=null)
//            {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
