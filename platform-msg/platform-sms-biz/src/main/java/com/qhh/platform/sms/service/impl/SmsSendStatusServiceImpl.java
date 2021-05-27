package com.qhh.platform.sms.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qhh.basic.base.service.SuperServiceImpl;
import com.qhh.platform.sms.dao.SmsSendStatusMapper;
import com.qhh.platform.sms.entity.SmsSendStatus;
import com.qhh.platform.sms.service.SmsSendStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 短信发送状态
 * </p>
 *
 * @author qhh
 * @date 2019-08-01
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class SmsSendStatusServiceImpl extends SuperServiceImpl<SmsSendStatusMapper, SmsSendStatus> implements SmsSendStatusService {

}
