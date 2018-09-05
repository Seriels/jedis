package redis.clients.jedis.plugin.service;

import redis.clients.jedis.plugin.params.ThrottleParam;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 13:54
 * @email 226804871@qq.com
 */
public interface JedisCommandsSupport {


    /**
     * BloomFilter
     *
     * @param key
     */
    public void  bFadd(byte[] key, byte[] value);

    public void bFmadd(byte[] key, byte[]... values) ;


    public void bFexists(byte[] key, byte[] values) ;


    public void bFmexists(byte[] key, byte[][] values) ;


    public void bFreserve(byte[] key) ;


    public void throttle(byte[] key, ThrottleParam throttleParam) ;
}
