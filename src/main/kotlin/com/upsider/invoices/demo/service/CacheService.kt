package com.upsider.invoices.demo.service

import jakarta.annotation.PostConstruct
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

/**
 * Cache Service
 *
 */
@Service
class CacheService constructor(val redisConnectionFactory: RedisConnectionFactory){

    /**
     * Init redis connection
     */
    val redisTemplate: RedisTemplate<String, Any> =  RedisTemplate<String, Any>()
    @PostConstruct
    fun init(){
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = JdkSerializationRedisSerializer()
        redisTemplate.afterPropertiesSet()
    }

    /**
     * Caches object in redis
     */
    fun saveForMilliseconds(key:String, obj:Any, timeout:Long){
        this.redisTemplate.opsForValue().set(key, obj, timeout, TimeUnit.MILLISECONDS)
    }

    /**
     * get object in redis
     */
    fun get(key:String) : Any? {
        return this.redisTemplate.opsForValue().get(key)
    }

    /**
     * check object exist redis or not
     */
    fun hasKey(key:String): Boolean {
        if (null != this.get(key)){
            return true
        }
        return false
    }
}