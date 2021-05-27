package com.qhh.platform.authority.service.auth.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qhh.platform.authority.dao.auth.RoleOrgMapper;
import com.qhh.platform.authority.entity.auth.RoleOrg;
import com.qhh.platform.authority.service.auth.RoleOrgService;
import com.qhh.basic.base.service.SuperServiceImpl;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色组织关系
 * </p>
 *
 * @author qhh
 * @date 2019-07-03
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class RoleOrgServiceImpl extends SuperServiceImpl<RoleOrgMapper, RoleOrg> implements RoleOrgService {
    @Override
    public List<Long> listOrgByRoleId(Long roleId) {
        List<RoleOrg> list = super.list(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
        return list.stream().mapToLong(RoleOrg::getOrgId).boxed().collect(Collectors.toList());
    }
}
