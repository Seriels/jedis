package redis.clients.jedis.plugin;

import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.util.SafeEncoder;

import java.util.Locale;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 9:35
 * @email 226804871@qq.com
 */
public class ProtocolSupport {


    /**
     * 布隆过滤器前缀
     */
    public static final String BLOOM_PREFIX = "BF.";
    /**
     * 限流
     */
    public static final String FUNNEL_PREFIX = "CL.";


    public static enum Command implements ProtocolCommand {
        /**
         * BloomFilter
         */
        ADD(ProtocolSupport.BLOOM_PREFIX), EXISTS(ProtocolSupport.BLOOM_PREFIX), MADD(ProtocolSupport.BLOOM_PREFIX), MEXISTS(ProtocolSupport.BLOOM_PREFIX), RESERVE(ProtocolSupport.BLOOM_PREFIX),
        /**
         * funnel   throttle
         */
        THROTTLE(ProtocolSupport.FUNNEL_PREFIX),
        /**
         * stream
         */
        XADD(""), XDEL(""), XRANGE(""), XLEN("");

        private String prefix = "";
        private final byte[] raw;

        Command(String prefix) {
            this.prefix = prefix;
            raw = SafeEncoder.encode(prefix+this.name());
        }

        @Override
        public byte[] getRaw() {
            return raw;
        }
    }

    public static enum Keyword {
        ;
        public final byte[] raw;

        Keyword() {
            raw = SafeEncoder.encode(this.name().toLowerCase(Locale.ENGLISH));
        }
    }
}
