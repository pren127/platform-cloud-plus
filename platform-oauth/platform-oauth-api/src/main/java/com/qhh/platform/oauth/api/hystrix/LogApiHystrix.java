package com.qhh.platform.oauth.api.hystrix;

import com.qhh.platform.oauth.api.LogApi;
import com.qhh.basic.base.R;
import com.qhh.basic.log.entity.OptLogDTO;
import org.springframework.stereotype.Component;

/**
 * 日志操作 熔断
 *
 * @author qhh
 * @date 2019/07/02
 */
@Component
public class LogApiHystrix implements LogApi {
    @Override
    public R<OptLogDTO> save(OptLogDTO log) {
        return R.timeout();
    }
}
