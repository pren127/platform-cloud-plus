package com.qhh.platform.common.constant;

/**
 * 业务常量
 *
 * @author qhh
 * @date 2019/08/06
 */
public interface BizConstant {
    /**
     * 工具类 需要扫描的包
     */
    String UTIL_PACKAGE = "com.qhh.basic";
    /**
     * 业务项目 需要扫描的包
     */
    String BUSINESS_PACKAGE = "com.qhh.platform";
    /**
     * 超级租户编码
     */
    String SUPER_TENANT = "admin";
    /**
     * 初始化的租户管理员角色
     */
    String INIT_ROLE_CODE = "SUPER_ADMIN";

    /**
     * 演示用的组织ID
     */
    Long DEMO_ORG_ID = 101L;
    /**
     * 角色前缀
     */
    String ROLE_PREFIX = "ROLE_";
    /**
     * 演示用的岗位ID
     */
    Long DEMO_STATION_ID = 101L;

    /**
     * 默认密码：123456
     */
    String DEF_PASSWORD = "123456";

    /**
     * 基础库
     */
    String BASE_DATABASE = "platform_base";
    /**
     * 扩展库
     */
    String EXTEND_DATABASE = "platform_extend";

    /**
     * 被T
     */
    String LOGIN_STATUS = "T";

    String AUTHORITY = "platform-authority-server";
    String FILE = "platform-file-server";
    String MSG = "platform-msg-server";
    String OAUTH = "platform-oauth-server";
    String GATE = "platform-gateway-server";
    String BASE_EXECUTOR = "platform-base-executor";
    String EXTEND_EXECUTOR = "platform-extend-executor";
    String ORDER = "platform-example-server";
    String DEMO = "platform-demo-server";

    /**
     * 初始化数据源时json的参数，
     * method 的可选值为 {INIT_DS_PARAM_METHOD_INIT} 和 {INIT_DS_PARAM_METHOD_REMOVE}
     */
    String INIT_DS_PARAM_METHOD = "method";
    /**
     * 初始化数据源时json的参数，
     * tenant 的值为 需要初始化的租户编码
     */
    String INIT_DS_PARAM_TENANT = "tenant";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_INIT = "init";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_REMOVE = "remove";
}
