package com.qhh.platform.msg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qhh.basic.base.service.SuperServiceImpl;
import com.qhh.platform.msg.dao.MsgReceiveMapper;
import com.qhh.platform.msg.entity.MsgReceive;
import com.qhh.platform.msg.service.MsgReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 消息中心 接收表
 * 全量数据
 * </p>
 *
 * @author qhh
 * @date 2019-08-01
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class MsgReceiveServiceImpl extends SuperServiceImpl<MsgReceiveMapper, MsgReceive> implements MsgReceiveService {

}
