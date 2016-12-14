package com.centit.dde.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * create by scaffold
 *
 * @author codefan@hotmail.com
 */
@Entity
@Table(name="D_EXCHANGE_TASK")
public class ExchangeTask implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    
    @Column(name="TASKID")
    @GeneratedValue(generator = "assignedGenerator")
    @GenericGenerator(name = "assignedGenerator", strategy = "assigned")
    private Long taskId;

    @Column(name="TASKNAME")
    private String taskName;
    
    @Column(name="TASKCRON")
    private String taskCron;

    @Column(name="TASKDESC")
    private String taskDesc;

    @Column(name="LASTRUNTIME")
    private Date lastRunTime;
    
    @Column(name="NEXTRUNTIME")
    private Date nextRunTime;
    
    @Column(name="ISVALID")
    private String isvalid;
    
    @Column(name="CREATETIME")
    private Date createTime;
    
    @Column(name="CREATED")
    private String created;
    
    @Column(name="CREATEDNAME")
    private String createdName;
    /**
     * '1: 直接交换 2 :导出离线文件 3：监控文件夹导入文件 4：调用接口 5:接口事件';
     */
    private String taskType;// char(1) default '1'  not null 
    private Date lastUpdateTime;//     date 
    private String storeIsolation;//      char(1)
    private String monitorFolder;//       varchar2(200);

    private Set<ExchangeTaskdetail> exchangeTaskdetails = null;// new
    // ArrayList<ExchangeTaskdetail>();
    private Set<TaskLog> taskLogs = null;// new ArrayList<TaskLog>();

    // Constructors

    /**
     * default constructor
     */
    public ExchangeTask() {
    }

    /**
     * minimal constructor
     */
    public ExchangeTask(Long taskId, String taskName, String taskCron) {

        this.taskId = taskId;

        this.taskName = taskName;
        this.taskCron = taskCron;
    }

    /**
     * full constructor
     */
    public ExchangeTask(Long taskId, String taskName, String taskCron,
                        String taskDesc, Date lastRunTime, Date nextRunTime,
                        String isValid, Date createTime, String created, String taskType,
                        Date lastUpdateTime, String storeIsolation, String monitorFolder) {

        this.taskId = taskId;

        this.taskType = taskType;
        this.taskName = taskName;
        this.taskCron = taskCron;
        this.taskDesc = taskDesc;
        this.lastRunTime = lastRunTime;
        this.nextRunTime = nextRunTime;
        this.isvalid = isValid;
        this.createTime = createTime;
        this.created = created;

        this.lastUpdateTime = lastUpdateTime;
        this.storeIsolation = storeIsolation;
        this.monitorFolder = monitorFolder;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    // Property accessors

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * '1: 直接交换 2 :导出离线文件 3：监控文件夹导入文件 4：调用接口 5:接口事件';
     *
     * @return
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * '1: 直接交换 2 :导出离线文件 3：监控文件夹导入文件 4：调用接口 5:接口事件';
     *
     * @param taskType
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskCron() {
        return this.taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public String getTaskDesc() {
        return this.taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Date getLastRunTime() {
        return this.lastRunTime;
    }

    public void setLastRunTime(Date lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    public Date getNextRunTime() {
        return this.nextRunTime;
    }

    public void setNextRunTime(Date nextRunTime) {
        this.nextRunTime = nextRunTime;
    }

    public String getIsvalid() {
        return this.isvalid;
    }

    public void setIsvalid(String isValid) {
        this.isvalid = isValid;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lasUpdateTime) {
        this.lastUpdateTime = lasUpdateTime;
    }

    public String getStoreIsolation() {
        return storeIsolation;
    }

    public void setStoreIsolation(String storeIsolation) {
        this.storeIsolation = storeIsolation;
    }

    public String getMonitorFolder() {
        return monitorFolder;
    }

    public void setMonitorFolder(String monitorFolder) {
        this.monitorFolder = monitorFolder;
    }

    public Set<ExchangeTaskdetail> getExchangeTaskdetails() {
        if (this.exchangeTaskdetails == null)
            this.exchangeTaskdetails = new HashSet<ExchangeTaskdetail>();
        return this.exchangeTaskdetails;
    }

    public void setExchangeTaskdetails(
            Set<ExchangeTaskdetail> exchangeTaskdetails) {
        this.exchangeTaskdetails = exchangeTaskdetails;
    }

    public void addExchangeTaskdetail(ExchangeTaskdetail exchangeTaskdetail) {
        if (this.exchangeTaskdetails == null)
            this.exchangeTaskdetails = new HashSet<ExchangeTaskdetail>();
        this.exchangeTaskdetails.add(exchangeTaskdetail);
    }

    public void removeExchangeTaskdetail(ExchangeTaskdetail exchangeTaskdetail) {
        if (this.exchangeTaskdetails == null)
            return;
        this.exchangeTaskdetails.remove(exchangeTaskdetail);
    }

    public ExchangeTaskdetail newExchangeTaskdetail() {
        ExchangeTaskdetail res = new ExchangeTaskdetail();

        res.setTaskId(this.getTaskId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
     */
    public void replaceExchangeTaskdetails(
            List<ExchangeTaskdetail> exchangeTaskdetails) {
        List<ExchangeTaskdetail> newObjs = new ArrayList<ExchangeTaskdetail>();
        for (ExchangeTaskdetail p : exchangeTaskdetails) {
            if (p == null)
                continue;
            ExchangeTaskdetail newdt = newExchangeTaskdetail();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<ExchangeTaskdetail> oldObjs = new HashSet<ExchangeTaskdetail>();
        oldObjs.addAll(getExchangeTaskdetails());

        for (Iterator<ExchangeTaskdetail> it = oldObjs.iterator(); it.hasNext(); ) {
            ExchangeTaskdetail odt = it.next();
            found = false;
            for (ExchangeTaskdetail newdt : newObjs) {
                if (odt.getCid().equals(newdt.getCid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeExchangeTaskdetail(odt);
        }
        oldObjs.clear();
        // insert or update
        for (ExchangeTaskdetail newdt : newObjs) {
            found = false;
            for (Iterator<ExchangeTaskdetail> it = getExchangeTaskdetails()
                    .iterator(); it.hasNext(); ) {
                ExchangeTaskdetail odt = it.next();
                if (odt.getCid().equals(newdt.getCid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addExchangeTaskdetail(newdt);
        }
    }

    public Set<TaskLog> getTaskLogs() {
        if (this.taskLogs == null)
            this.taskLogs = new HashSet<TaskLog>();
        return this.taskLogs;
    }

    public void setTaskLogs(Set<TaskLog> taskLogs) {
        this.taskLogs = taskLogs;
    }

    public void addTaskLog(TaskLog taskLog) {
        if (this.taskLogs == null)
            this.taskLogs = new HashSet<TaskLog>();
        this.taskLogs.add(taskLog);
    }

    public void removeTaskLog(TaskLog taskLog) {
        if (this.taskLogs == null)
            return;
        this.taskLogs.remove(taskLog);
    }

    public TaskLog newTaskLog() {
        TaskLog res = new TaskLog();

        res.setTaskId(this.getTaskId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
     */
    public void replaceTaskLogs(List<TaskLog> taskLogs) {
        List<TaskLog> newObjs = new ArrayList<TaskLog>();
        for (TaskLog p : taskLogs) {
            if (p == null)
                continue;
            TaskLog newdt = newTaskLog();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<TaskLog> oldObjs = new HashSet<TaskLog>();
        oldObjs.addAll(getTaskLogs());

        for (Iterator<TaskLog> it = oldObjs.iterator(); it.hasNext(); ) {
            TaskLog odt = it.next();
            found = false;
            for (TaskLog newdt : newObjs) {
                if (odt.getLogId().equals(newdt.getLogId())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeTaskLog(odt);
        }
        oldObjs.clear();
        // insert or update
        for (TaskLog newdt : newObjs) {
            found = false;
            for (Iterator<TaskLog> it = getTaskLogs().iterator(); it.hasNext(); ) {
                TaskLog odt = it.next();
                if (odt.getLogId().equals(newdt.getLogId())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addTaskLog(newdt);
        }
    }

    public void copy(ExchangeTask other) {

        this.setTaskId(other.getTaskId());

        this.taskName = other.getTaskName();
        this.taskCron = other.getTaskCron();
        this.taskDesc = other.getTaskDesc();
        this.lastRunTime = other.getLastRunTime();
        this.nextRunTime = other.getNextRunTime();
        this.isvalid = other.getIsvalid();
        this.createTime = other.getCreateTime();
        this.created = other.getCreated();

        this.exchangeTaskdetails = other.getExchangeTaskdetails();
        this.taskLogs = other.getTaskLogs();

        this.lastUpdateTime = other.getLastUpdateTime();
        this.storeIsolation = other.getStoreIsolation();
        this.monitorFolder = other.getMonitorFolder();

    }

    public void copyNotNullProperty(ExchangeTask other) {

        if (other.getTaskId() != null)
            this.setTaskId(other.getTaskId());

        if (other.getTaskName() != null)
            this.taskName = other.getTaskName();
        if (other.getTaskCron() != null)
            this.taskCron = other.getTaskCron();
        if (other.getTaskDesc() != null)
            this.taskDesc = other.getTaskDesc();
        if (other.getLastRunTime() != null)
            this.lastRunTime = other.getLastRunTime();
        if (other.getNextRunTime() != null)
            this.nextRunTime = other.getNextRunTime();
        if (other.getIsvalid() != null)
            this.isvalid = other.getIsvalid();
        if (other.getCreateTime() != null)
            this.createTime = other.getCreateTime();
        if (other.getCreated() != null)
            this.created = other.getCreated();

        if (other.getLastUpdateTime() != null)
            this.lastUpdateTime = other.getLastUpdateTime();
        if (other.getStoreIsolation() != null)
            this.storeIsolation = other.getStoreIsolation();
        if (other.getMonitorFolder() != null)
            this.monitorFolder = other.getMonitorFolder();

        this.taskLogs = other.getTaskLogs();
        this.exchangeTaskdetails = other.getExchangeTaskdetails();
    }

    public void clearProperties() {

        this.taskName = null;
        this.taskCron = null;
        this.taskDesc = null;
        this.lastRunTime = null;
        this.nextRunTime = null;
        this.isvalid = null;
        this.createTime = null;
        this.created = null;
        this.lastUpdateTime = null;
        this.storeIsolation = null;
        this.monitorFolder = null;

        this.exchangeTaskdetails = new HashSet<ExchangeTaskdetail>();
        this.taskLogs = new HashSet<TaskLog>();
    }
}
