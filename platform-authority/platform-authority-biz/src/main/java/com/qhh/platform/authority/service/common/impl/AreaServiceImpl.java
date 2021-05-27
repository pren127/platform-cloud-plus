package com.qhh.platform.authority.service.common.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.qhh.basic.base.service.SuperCacheServiceImpl;
import com.qhh.basic.cache.model.CacheKeyBuilder;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import com.qhh.platform.authority.dao.common.AreaMapper;
import com.qhh.platform.authority.entity.common.Area;
import com.qhh.platform.authority.service.common.AreaService;
import com.qhh.platform.common.cache.common.AreaCacheKeyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 地区表
 * </p>
 *
 * @author qhh
 * @date 2019-07-02
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class AreaServiceImpl extends SuperCacheServiceImpl<AreaMapper, Area> implements AreaService {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new AreaCacheKeyBuilder();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recursively(List<Long> ids) {
        boolean removeFlag = removeByIds(ids);
        delete(ids);
        return removeFlag;
    }

    private void delete(List<Long> ids) {
        // 查询子节点
        List<Long> childIds = super.listObjs(Wraps.<Area>lbQ().select(Area::getId).in(Area::getParentId, ids), Convert::toLong);
        if (!childIds.isEmpty()) {
            removeByIds(childIds);
            delete(childIds);
        }
        log.debug("退出地区数据递归");
    }
}
