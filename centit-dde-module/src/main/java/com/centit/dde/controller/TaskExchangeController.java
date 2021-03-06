package com.centit.dde.controller;

import com.alibaba.fastjson.JSONObject;
import com.centit.dde.datamoving.service.TaskRun;
import com.centit.dde.po.TaskExchange;
import com.centit.dde.po.TaskLog;
import com.centit.dde.services.TaskExchangeManager;
import com.centit.dde.services.TaskLogManager;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.controller.WrapUpResponseBody;
import com.centit.framework.core.dao.PageQueryResult;
import com.centit.product.dataopt.core.BizModel;
import com.centit.product.datapacket.po.DataPacket;
import com.centit.product.datapacket.service.DataPacketService;
import com.centit.product.datapacket.utils.DataPacketUtil;
import com.centit.product.datapacket.vo.DataPacketSchema;
import com.centit.support.database.utils.PageDesc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TaskExchangeController
 * @Date 2019/3/20 16:23
 * @Version 1.0
 */
@RestController
@Api(value = "交换任务", tags = "交换任务")
@RequestMapping(value = "taskExchange")
public class TaskExchangeController extends BaseController{

    private static final Log log = LogFactory.getLog(TaskExchangeController.class);

    @Autowired
    private TaskExchangeManager taskExchangeManager;
    @Autowired
    private TaskLogManager taskLogManager;
    @Autowired
    private DataPacketService dataPacketService;
    @Autowired
    private TaskRun taskRun;
    @PostMapping
    @ApiOperation(value = "新增任务")
    @WrapUpResponseBody
    public void createTaskExchange(TaskExchange taskExchange){
        taskExchange.setExchangeDescJson(StringEscapeUtils.unescapeHtml4(taskExchange.getExchangeDescJson()));
        taskExchangeManager.createTaskExchange(taskExchange);
    }

    @PutMapping(value = "/{taskId}")
    @ApiImplicitParam(name = "taskId", value = "任务ID")
    @ApiOperation(value = "更新任务")
    @WrapUpResponseBody
    public void updateTaskExchange(@PathVariable String taskId, @RequestBody TaskExchange taskExchange){
        taskExchange.setTaskId(taskId);
        taskExchange.setExchangeDescJson(StringEscapeUtils.unescapeHtml4(taskExchange.getExchangeDescJson()));
        if(taskExchange.getTaskCron()==null) taskExchange.setTaskCron("");
        taskExchangeManager.updateTaskExchange(taskExchange);
    }

    @DeleteMapping(value = "/{taskId}")
    @ApiImplicitParam(name = "taskId", value = "任务ID")
    @ApiOperation(value = "删除任务")
    @WrapUpResponseBody
    public void delTaskExchange(@PathVariable String taskId){
       taskExchangeManager.delTaskExchangeById(taskId);
    }

    @GetMapping
    @ApiOperation(value = "查询所有任务")
    @WrapUpResponseBody
    public PageQueryResult<TaskExchange> listTaskExchange(PageDesc pageDesc,HttpServletRequest request){
        Map<String, Object> parameters = collectRequestParameters(request);
        List<TaskExchange> taskExchanges = taskExchangeManager.listTaskExchange(parameters, pageDesc);
        return PageQueryResult.createResult(taskExchanges,pageDesc);
    }


    @GetMapping(value = "/{taskId}")
    @ApiOperation(value = "查询一个任务")
    @WrapUpResponseBody
    public TaskExchange getTaskExchange(@PathVariable String taskId){
        TaskExchange taskExchange = taskExchangeManager.getTaskExchange(taskId);
        return taskExchange;
    }
    @ApiOperation(value = "获取任务的数据包模式")
    @GetMapping(value = "/schema/{taskId}")
    @WrapUpResponseBody
    public DataPacketSchema getDataPacketSchema(@PathVariable String taskId){
        TaskExchange taskExchange = taskExchangeManager.getTaskExchange(taskId);
        DataPacket dataPacket = dataPacketService.getDataPacket(taskExchange.getPacketId());
        DataPacketSchema schema = DataPacketSchema.valueOf(dataPacket);
        if(dataPacket!=null) {
            JSONObject obj = dataPacket.getDataOptDesc();
            if (obj != null) {
                schema= DataPacketUtil.calcDataPacketSchema(schema, obj);
            }
            obj = taskExchange.getExchangeDesc();
            if (obj != null) {
                schema= DataPacketUtil.calcDataPacketSchema(schema, obj);
            }
        }
        return schema;
    }

    @ApiOperation(value = "编辑数据交换数据处理描述信息")
    @PutMapping(value = "/opt/{taskId}")
    @WrapUpResponseBody
    public void updateDataPacketOpt(@PathVariable String taskId, @RequestBody String exchangeOptJson){
        taskExchangeManager.updateExchangeOptJson(taskId, exchangeOptJson);
    }
    @GetMapping(value = "/run/{taskId}")
    @ApiOperation(value = "立即执行任务")
    @WrapUpResponseBody
    public BizModel runTaskExchange(@PathVariable String taskId){
        TaskExchange taskExchange = taskExchangeManager.getTaskExchange(taskId);
        TaskLog taskLog = new TaskLog();
        taskLog.setTaskId(taskExchange.getTaskId());
        taskLog.setRunBeginTime(new Date());
        taskLog.setRunType(taskExchange.getTaskName());
        taskLogManager.createTaskLog(taskLog);
        BizModel bizModel=taskRun.runTask(taskLog.getLogId());
        return bizModel;
    }
    @GetMapping(value = "/exist/{applicationId}/{interfaceName}")
    @ApiOperation(value = "接口名称是否已存在")
    @WrapUpResponseBody
    public Boolean isExist(@PathVariable String applicationId, @PathVariable String interfaceName){
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName",interfaceName);
        params.put("applicationId",applicationId);
        List<TaskExchange> list = taskExchangeManager.listTaskExchange(params,new PageDesc());
        if (list.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
