package com.qhh.platform.authority.controller.auth;

import com.qhh.basic.annotation.security.PreAuth;
import com.qhh.basic.base.R;
import com.qhh.platform.authority.dto.auth.Online;
import com.qhh.platform.authority.service.auth.OnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * token
 * </p>
 *
 * @author qhh
 * @date 2020-04-02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/online")
@Api(value = "OnlineController", tags = "在线用户")
@PreAuth(replace = "authority:online:")
@RequiredArgsConstructor
public class OnlineController {
    private final OnlineService onlineService;

    @PostMapping(value = "/list")
    @PreAuth("hasAnyPermission('{}view')")
    public R<List<Online>> list(@RequestParam(required = false) String name) {
        return R.success(onlineService.list(name));
    }

    @ApiOperation(value = "T人", notes = "T人")
    @PostMapping(value = "/t")
    @PreAuth("hasAnyPermission('{}delete')")
    public R<Boolean> logout(String userToken, Long userId, String clientId) {
        return R.success(onlineService.clear(userToken, userId, clientId));
    }

}
