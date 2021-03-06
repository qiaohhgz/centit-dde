package com.centit.dde.dao;

import com.centit.dde.po.TaskDetailLog;
import com.centit.framework.core.dao.CodeBook;
import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.framework.jdbc.dao.DatabaseOptUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskDetailLogDao extends BaseDaoImpl<TaskDetailLog,Long> {

    public static final Log logger = LogFactory.getLog(TaskDetailLogDao.class);
    @Override
    public Map<String, String> getFilterField() {
        Map<String, String> filterField =new HashMap<String, String>();

            filterField.put("logDetailId", CodeBook.EQUAL_HQL_ID);

            filterField.put("taskId", CodeBook.LIKE_HQL_ID);

            filterField.put("logId", CodeBook.LIKE_HQL_ID);

            filterField.put("logType", CodeBook.LIKE_HQL_ID);

            filterField.put("runBeginTime", "runBeginTime>=?");
            filterField.put("runBeginTime2", "runBeginTime<=?");

            filterField.put("runEndTime", CodeBook.LIKE_HQL_ID);

            filterField.put("logInfo", CodeBook.LIKE_HQL_ID);

            filterField.put("successPieces", CodeBook.LIKE_HQL_ID);

            filterField.put("errorPieces", CodeBook.LIKE_HQL_ID);
        return filterField;
    }

    public Long getTaskDetailLogId() {
        return DatabaseOptUtils.getSequenceNextValue(this, "D_TASKDETAILLOGID");
    }

}
