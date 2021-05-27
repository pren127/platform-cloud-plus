package com.qhh.platform.oauth.api.hystrix;

import com.qhh.platform.oauth.api.RoleApi;
import com.qhh.basic.base.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色查询API
 *
 * @author qhh
 * @date 2019/08/02
 */
@Component
public class RoleApiFallback implements RoleApi {
    @Override
    public R<List<Long>> findUserIdByCode(String[] codes) {
        return R.timeout();
    }
}
