package com.sqq.redis;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOperator {
	private static final Logger log = LoggerFactory.getLogger(RedisOperator.class);
	private static final String KEY_SPLIT = ":";	// 用于隔开项目前缀与缓存键值

	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 设置缓存
	 * 
	 * @param prefix
	 *            项目前缀（用于区分缓存，防止缓存键值重复）
	 * @param key
	 *            缓存key
	 * @param value
	 *            缓存value
	 */
	public void set(String prefix, String key, Object value) {
		redisTemplate.opsForValue().set(prefix + KEY_SPLIT + key, value);
		log.debug("RedisService:set cache key={},value={}", prefix + KEY_SPLIT + key, value);
	}

	/**
	 * 设置缓存，并且指定过期时间
	 * 
	 * @param prefix
	 *            项目前缀（用于区分缓存，防止缓存键值重复）
	 * @param key
	 *            缓存key
	 * @param value
	 *            缓存value
	 * @param expireTime
	 *            过期时间
	 * @param timeUnit
	 *            过期时间单位
	 */
	public void setWithExpireTime(String prefix, String key, Object value, Long expireTime, TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(prefix + KEY_SPLIT + key, value, expireTime, timeUnit);
		log.info("RedisService:setWithExpireTime cache key={},value={},expireTime={},timeUnit={}",
				prefix + KEY_SPLIT + key, value, expireTime, timeUnit);
	}

	/**
	 * 获取指定key的缓存
	 * 
	 * @param prefix
	 *            项目前缀（用于区分缓存，防止缓存键值重复）
	 * @param key
	 *            缓存key
	 * @return 缓存value
	 */
	public Object get(String prefix, String key) {
		Object value = redisTemplate.opsForValue().get(prefix + KEY_SPLIT + key);
		log.debug("RedisService:get cache key={},value={}", prefix + KEY_SPLIT + key, value);
		return value;
	}

	/**
	 * 获取指定key的缓存
	 * 
	 * @param key
	 *            缓存key
	 * @return
	 */
	public Object get(String key) {
		Object value = redisTemplate.opsForValue().get(key);
		log.debug("RedisService:get cache key={},value={}", key, value);
		return value;
	}

	/**
	 * 删除指定key的缓存
	 * 
	 * @param prefix
	 *            项目前缀（用于区分缓存，防止缓存键值重复）
	 * @param key
	 *            缓存key
	 */
	public void deleteWithPrefix(String prefix, String key) {
		redisTemplate.delete(prefix + KEY_SPLIT + key);
		log.debug("RedisService:deleteWithPrefix cache key={},value={}", prefix + KEY_SPLIT + key);
	}

	/**
	 * 删除指定key的缓存
	 * 
	 * @param key
	 *            缓存key
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
		log.debug("RedisService:delete cache key={}", key);
	}

	/**
	 * 自增
	 * 
	 * @param prefix
	 *            项目前缀（用于区分缓存，防止缓存键值重复）
	 * @param key
	 *            缓存key
	 * @param span
	 *            自增跨度 当span大于零时，自增；当span小于零时，自减
	 * @return
	 */
	public Long incr(String prefix, String key, Long span) {
		Long value = redisTemplate.opsForValue().increment(prefix + KEY_SPLIT + key, span);
		log.debug("RedisService:incr cache key={}", prefix + KEY_SPLIT + key, value);
		return value;
	}
}
