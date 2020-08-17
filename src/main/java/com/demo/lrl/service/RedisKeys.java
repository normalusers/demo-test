package com.demo.lrl.service;

/**\
 * @author liuruilin
 */
public interface RedisKeys {
    /**
     * api的限制配置，hash key
     */
    String REQUEST_LIMIT_CONFIG = "request_limit_config";

    /**
     * api的请求的次数
     */
    String REQUEST_LIMIT = "request_limit";
}
