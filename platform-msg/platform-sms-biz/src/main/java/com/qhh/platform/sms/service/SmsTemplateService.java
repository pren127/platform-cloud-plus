package com.qhh.platform.sms.service;

import com.qhh.basic.base.service.SuperService;
import com.qhh.platform.sms.entity.SmsTemplate;

/**
 * <p>
 * 业务接口
 * 短信模板
 * </p>
 *
 * @author qhh
 * @date 2019-08-01
 */
public interface SmsTemplateService extends SuperService<SmsTemplate> {
    /**
     * 保存模板，并且将模板内容解析成json格式
     *
     * @param smsTemplate 短信模版
     */
    void saveTemplate(SmsTemplate smsTemplate);

    /**
     * 修改
     *
     * @param smsTemplate 短信模版
     */
    void updateTemplate(SmsTemplate smsTemplate);
}
