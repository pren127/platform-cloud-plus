package com.qhh.platform.msg.api.fallback;

import com.qhh.basic.base.R;
import com.qhh.platform.msg.api.SmsApi;
import com.qhh.platform.sms.dto.SmsSendTaskDTO;
import com.qhh.platform.sms.dto.VerificationCodeDTO;
import com.qhh.platform.sms.entity.SmsTask;
import org.springframework.stereotype.Component;

/**
 * 熔断
 *
 * @author qhh
 * @date 2019/07/25
 */
@Component
public class SmsApiFallback implements SmsApi {
    @Override
    public R<SmsTask> send(SmsSendTaskDTO smsTaskDTO) {
        return R.timeout();
    }

    @Override
    public R<Boolean> sendCode(VerificationCodeDTO data) {
        return R.timeout();
    }

    @Override
    public R<Boolean> verification(VerificationCodeDTO data) {
        return R.timeout();
    }
}
