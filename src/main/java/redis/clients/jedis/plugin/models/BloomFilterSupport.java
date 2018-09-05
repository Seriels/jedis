package redis.clients.jedis.plugin.models;

import redis.clients.jedis.plugin.params.ThrottleParam;

import java.util.List;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 14:07
 * @email 226804871@qq.com
 */
public interface BloomFilterSupport {

    /**
     * BloomFilter
     */
    Long bFadd(String key, String value);

    List<Long> bFmadd(String key, String... values);

    Long bFexists(String key, String values);

    List<Long> bFmexists(String key, String... values);

    String bFreserve(String key);



}
