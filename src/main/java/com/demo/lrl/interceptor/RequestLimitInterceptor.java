package com.demo.lrl.interceptor;

import com.demo.lrl.config.ObjectRedisTemplate;
import com.demo.lrl.config.RequestLimitConfig;
import com.demo.lrl.service.RedisKeys;
import org.jooq.tools.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author liuruilin
 */
@Component
public class RequestLimitInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(RequestLimitInterceptor.class);
    @Autowired
    private ObjectRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 收到请求的uri
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();

        if (!StringUtils.isBlank(contextPath) && !"/".equals(contextPath)) {
            uri = uri.substring(uri.indexOf(contextPath), contextPath.length());
        }
        log.info("uri = {}" , uri);
        // 尝试从hash中读取得到当前接口的限流配置
        String key = RedisKeys.REQUEST_LIMIT_CONFIG;
        RequestLimitConfig requestLimitConfig = (RequestLimitConfig) this.redisTemplate.opsForHash().get(key, uri);

        if (requestLimitConfig == null) {
            log.info("该uri={}没有限流配置", uri);
            return true;
        }

        String limitKey = RedisKeys.REQUEST_LIMIT + ":" + uri;

        Long count = this.redisTemplate.opsForValue().increment(limitKey);
        if (count == null) {
            log.info("参数有误");
            return false;
        }
        if (count == 1) {
            this.redisTemplate.expire(limitKey, requestLimitConfig.getTime(), requestLimitConfig.getTimeUnit());
            log.info("设置过期时间为 : time = {}, timeUnit = {} ", requestLimitConfig.getTime(), requestLimitConfig.getTimeUnit());
        }
        if (count > requestLimitConfig.getLimit()) {
            // 限定时间内，请求超出限制，响应客户端消息
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write("服务器繁忙，请重试");
            return false;
        }
        return true;
    }
}
