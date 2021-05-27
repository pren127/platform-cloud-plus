package com.qhh.platform.authority.service.common;

import com.qhh.basic.base.service.SuperService;
import com.qhh.basic.log.entity.OptLogDTO;
import com.qhh.platform.authority.dto.common.OptLogResult;
import com.qhh.platform.authority.entity.common.OptLog;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务接口
 * 系统日志
 * </p>
 *
 * @author qhh
 * @date 2019-07-02
 */
public interface OptLogService extends SuperService<OptLog> {

    /**
     * 保存日志
     *
     * @param entity 操作日志
     * @return 是否成功
     */
    boolean save(OptLogDTO entity);

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);

    /**
     * 查询操作日志详情
     *
     * @param id id
     * @return 详情
     */
    OptLogResult getOptLogResultById(Long id);
}
