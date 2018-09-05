package redis.clients.jedis.plugin.models;

import redis.clients.jedis.plugin.params.ThrottleParam;

import java.util.List;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 14:09
 * @email 226804871@qq.com
 */
public interface FunnelSupport {

    public List<Long> throttle(String key, ThrottleParam throttleParam);
}
