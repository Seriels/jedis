package redis.clients.jedis.tests.models;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.plugin.JedisSupport;
import redis.clients.jedis.plugin.params.ThrottleParam;

import java.util.List;

/**
 * @author pz
 * @version 0.1
 * @date 2018/9/5 16:32
 * @email 226804871@qq.com
 */
public class MyBloomFilter {

    @Test
    public void  Mttest(){
        try (JedisSupport jedis4 = new JedisSupport(new Jedis("192.168.236.135", 6379))){
          ///   MyBloomFilter
            jedis4.auth("passwd123");
            Long aLong = jedis4.bFadd("txtx", "1234");
            System.out.println(aLong);
            List<Long> test = jedis4.bFmexists("txtx", "1234","2345");
            test.forEach(System.out::println);

          ///  漏斗
            for (int i = 0; i < 6; i++) {
                List<Long> getTest = jedis4.throttle("getTest", new ThrottleParam());
                getTest.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
