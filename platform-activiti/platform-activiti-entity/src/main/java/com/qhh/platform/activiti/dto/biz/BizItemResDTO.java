package com.qhh.platform.activiti.dto.biz;

import com.qhh.platform.activiti.entity.biz.BizItem;
import com.qhh.platform.authority.entity.auth.User;
import lombok.Data;

/**
 * <p>
 * 实体类
 *
 * </p>
 *
 * @author qhh
 * @since 2020-08-19
 */
@Data
public class BizItemResDTO extends BizItem{

    /**
     * 实体项公共信息-用户
     */
    private User cUser;
}
