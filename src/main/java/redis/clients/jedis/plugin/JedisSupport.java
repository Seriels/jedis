package redis.clients.jedis.plugin;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.plugin.models.BloomFilterSupport;
import redis.clients.jedis.plugin.models.FunnelSupport;
import redis.clients.jedis.plugin.params.ThrottleParam;
import redis.clients.jedis.plugin.service.ClientSupport;
import redis.clients.jedis.util.SafeEncoder;

import java.io.Closeable;
import java.util.List;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 14:30
 * @email 226804871@qq.com
 */
public class JedisSupport    implements BloomFilterSupport, FunnelSupport,AutoCloseable{

     private ClientSupport clientSupport;

     private Client client;

     private Jedis jedis;

    public JedisSupport(Jedis jedis ) {
        this.jedis=jedis;
        this. client = jedis.getClient();
        this.clientSupport = new ClientSupport(client);
    }

    public String auth(final String password) {
        return  jedis.auth(password);
    }

    /**
     * BloomFilter
     *
     * @param key
     */
    @Override
    public Long bFadd(String key,String value) {
        clientSupport.bFadd(SafeEncoder.encode(key), SafeEncoder.encode(value));
        return clientSupport.getIntegerReply();
    }

    @Override
    public List<Long> bFmadd(String key, String... values) {
        clientSupport.bFmadd(SafeEncoder.encode(key), SafeEncoder.encodeMany(values));
        return clientSupport.getIntegerMultiBulkReply();
    }

    @Override
    public Long bFexists(String key,String values) {
        clientSupport.bFexists(SafeEncoder.encode(key),SafeEncoder.encode(values));
        return clientSupport.getIntegerReply();
    }

    @Override
    public List<Long> bFmexists(String key, String... values) {
        clientSupport.bFmexists(SafeEncoder.encode(key), SafeEncoder.encodeMany(values));
        return clientSupport.getIntegerMultiBulkReply();
    }

    @Override
    public String bFreserve(String key) {
        return null;
    }

    @Override
    public List<Long> throttle(String key , ThrottleParam throttleParam) {
        clientSupport.throttle(SafeEncoder.encode(key),throttleParam);
        return clientSupport.getIntegerMultiBulkReply();
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     *
     * <p>While this interface method is declared to throw {@code
     * Exception}, implementers are <em>strongly</em> encouraged to
     * declare concrete implementations of the {@code close} method to
     * throw more specific exceptions, or to throw no exception at all
     * if the close operation cannot fail.
     *
     * <p> Cases where the close operation may fail require careful
     * attention by implementers. It is strongly advised to relinquish
     * the underlying resources and to internally <em>mark</em> the
     * resource as closed, prior to throwing the exception. The {@code
     * close} method is unlikely to be invoked more than once and so
     * this ensures that the resources are released in a timely manner.
     * Furthermore it reduces problems that could arise when the resource
     * wraps, or is wrapped, by another resource.
     *
     * <p><em>Implementers of this interface are also strongly advised
     * to not have the {@code close} method throw {@link
     * InterruptedException}.</em>
     * <p>
     * This exception interacts with a thread's interrupted status,
     * and runtime misbehavior is likely to occur if an {@code
     * InterruptedException} is {@linkplain Throwable#addSuppressed
     * suppressed}.
     * <p>
     * More generally, if it would cause problems for an
     * exception to be suppressed, the {@code AutoCloseable.close}
     * method should not throw it.
     *
     * <p>Note that unlike the {@link Closeable#close close}
     * method of {@link Closeable}, this {@code close} method
     * is <em>not</em> required to be idempotent.  In other words,
     * calling this {@code close} method more than once may have some
     * visible side effect, unlike {@code Closeable.close} which is
     * required to have no effect if called more than once.
     * <p>
     * However, implementers of this interface are strongly encouraged
     * to make their {@code close} methods idempotent.
     *
     * @throws Exception if this resource cannot be closed
     */
    @Override
    public void close() throws Exception {
        jedis.close();
    }




}
