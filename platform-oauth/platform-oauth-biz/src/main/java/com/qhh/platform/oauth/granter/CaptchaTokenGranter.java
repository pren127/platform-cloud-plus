package com.qhh.platform.oauth.granter;

import com.qhh.basic.base.R;
import com.qhh.basic.database.properties.DatabaseProperties;
import com.qhh.basic.exception.BizException;
import com.qhh.basic.jwt.TokenUtil;
import com.qhh.basic.jwt.model.AuthInfo;
import com.qhh.basic.utils.SpringUtils;
import com.qhh.platform.authority.dto.auth.LoginParamDTO;
import com.qhh.platform.authority.service.auth.ApplicationService;
import com.qhh.platform.authority.service.auth.OnlineService;
import com.qhh.platform.authority.service.auth.UserService;
import com.qhh.platform.oauth.event.LoginEvent;
import com.qhh.platform.oauth.event.model.LoginStatusDTO;
import com.qhh.platform.oauth.service.ValidateCodeService;
import com.qhh.platform.tenant.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.qhh.platform.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

/**
 * 验证码TokenGranter
 *
 * @author qhh
 */
@Component(GRANT_TYPE)
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "captcha";
    private final ValidateCodeService validateCodeService;

    public CaptchaTokenGranter(TokenUtil tokenUtil, UserService userService,
                               TenantService tenantService, ApplicationService applicationService,
                               DatabaseProperties databaseProperties, ValidateCodeService validateCodeService,
                               OnlineService onlineService) {
        super(tokenUtil, userService, tenantService, applicationService, databaseProperties, onlineService);
        this.validateCodeService = validateCodeService;
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
        if (!check.getIsSuccess()) {
            String msg = check.getMsg();
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
            throw BizException.validFail(check.getMsg());
        }

        return login(loginParam);
    }

}
