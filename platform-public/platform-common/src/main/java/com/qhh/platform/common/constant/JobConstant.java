package com.qhh.platform.common.constant;

/**
 * 定时任务 常量
 *
 * @author qhh
 * @date 2021/1/8 10:16 上午
 */
public interface JobConstant {

    /**
     * 默认的定时任务组
     */
    String DEF_BASE_JOB_GROUP_NAME = "platform-base-executor";
    String DEF_EXTEND_JOB_GROUP_NAME = "platform-extend-executor";
    /**
     * 短信发送处理器
     */
    String SMS_SEND_JOB_HANDLER = "smsSendJobHandler";
}
