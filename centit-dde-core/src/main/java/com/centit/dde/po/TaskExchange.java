package com.centit.dde.po;

import com.centit.support.database.orm.GeneratorType;
import com.centit.support.database.orm.ValueGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName TaskExchange
 * @Date 2019/3/20 9:21
 * @Version 1.0
 */
@Data
@Entity
@ApiModel
@Table(name = "D_EXCHANGE_TASK")
public class TaskExchange implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TASK_ID")
    @ApiModelProperty(value = "任务ID", hidden = true)
    @ValueGenerator(strategy = GeneratorType.UUID)
    private String taskId;

    @Column(name = "PACKET_ID")
    @ApiModelProperty(value = "包ID", required = true)
    @NotBlank
    private String packetId;

    @Column(name = "TASK_NAME")
    @ApiModelProperty(value = "任务名称", required = true)
    private String taskName;

    @Column(name = "TASK_TYPE")
    @ApiModelProperty(value = "任务类型", required = true)
    @NotBlank
    private String taskType;

    @Column(name = "TASK_CRON")
    @ApiModelProperty(value = "任务执行定时器", hidden = true)
    private String taskCron;

    @Column(name = "TASK_DESC")
    @ApiModelProperty(value = "任务描述", hidden = true)
    private String taskDesc;

    @Column(name = "LAST_RUN_TIME")
    @ApiModelProperty(value = "上次执行时间", hidden = true)
    private Date lastRunTime;

    @Column(name = "NEXT_RUN_TIME")
    @ApiModelProperty(value = "下次执行时间", hidden = true)
    private Date nextRunTime;

    @Column(name = "IS_VALID")
    @ApiModelProperty(value = "是否启用", required = true)
    @NotBlank
    private String isValid;

    @Column(name = "CREATE_TIME")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @Column(name = "CREATED")
    @ApiModelProperty(value = "创建人员", hidden = true)
    private String created;

    @Column(name = "LAST_UPDATE_TIME")
    @ApiModelProperty(value = "最后更新时间", hidden = true)
    private Date lastUpdateTime;


}
