package redis.clients.jedis.plugin.models;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 14:10
 * @email 226804871@qq.com
 */
public interface StreamSupport {

    String xadd(String key);

    String xdel(String key);

    String xrange(String key);

    String xlen(String key);
}
