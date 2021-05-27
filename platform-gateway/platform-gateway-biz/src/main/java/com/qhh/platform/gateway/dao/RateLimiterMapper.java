package com.qhh.platform.gateway.dao;

import com.qhh.basic.base.mapper.SuperMapper;
import com.qhh.platform.gateway.entity.RateLimiter;
import org.springframework.stereotype.Repository;

/**
 * 限流
 *
 * @author qhh
 * @date 2020/8/5 上午10:31
 */
@Repository
public interface RateLimiterMapper extends SuperMapper<RateLimiter> {
}
