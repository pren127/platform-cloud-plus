package com.qhh.platform.tenant.api;

import com.qhh.basic.base.R;
import com.qhh.platform.tenant.dto.DataSourcePropertyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * msgs服务初始化数据源
 *
 * @author qhh
 * @date 2020年04月05日18:18:26
 */
@FeignClient(name = "${platform.feign.msg-server:platform-msg-server}", path = "/ds")
public interface MsgDsApi {

    /**
     * 初始化数据源
     *
     * @param tenantConnect
     * @return
     */
    @RequestMapping(value = "/initConnect", method = RequestMethod.POST)
    R<Boolean> initConnect(@RequestBody DataSourcePropertyDTO tenantConnect);

    /**
     * 删除数据源
     *
     * @param tenant
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    R<Boolean> remove(@RequestParam(value = "tenant") String tenant);
}
