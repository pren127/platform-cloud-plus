package com.qhh.platform.authority.service.common.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qhh.basic.base.service.SuperServiceImpl;
import com.qhh.basic.log.entity.OptLogDTO;
import com.qhh.basic.utils.BeanPlusUtil;
import com.qhh.platform.authority.dao.common.OptLogExtMapper;
import com.qhh.platform.authority.dao.common.OptLogMapper;
import com.qhh.platform.authority.dto.common.OptLogResult;
import com.qhh.platform.authority.entity.common.OptLog;
import com.qhh.platform.authority.entity.common.OptLogExt;
import com.qhh.platform.authority.service.common.OptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author qhh
 * @date 2019-07-02
 */
@Slf4j
@Service
@DS("#thread.tenant")
@RequiredArgsConstructor
public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {
    private final OptLogExtMapper optLogExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(OptLogDTO entity) {
        OptLog optLog = BeanPlusUtil.toBean(entity, OptLog.class);
        OptLogExt optLogExt = BeanPlusUtil.toBean(entity, OptLogExt.class);
        boolean bool = super.save(optLog);
        optLogExt.setId(optLog.getId());
        optLogExtMapper.insert(optLogExt);
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public OptLogResult getOptLogResultById(Long id) {
        OptLog opt = getById(id);
        OptLogResult result = BeanPlusUtil.toBean(opt, OptLogResult.class);
        OptLogExt optLogExt = optLogExtMapper.selectById(id);
        BeanPlusUtil.copyProperties(optLogExt, result);
        return result;
    }

}
