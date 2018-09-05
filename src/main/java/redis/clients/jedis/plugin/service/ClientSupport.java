package redis.clients.jedis.plugin.service;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.BuilderFactory;
import redis.clients.jedis.Client;
import redis.clients.jedis.plugin.ProtocolSupport;
import redis.clients.jedis.plugin.params.ThrottleParam;
import redis.clients.jedis.util.SafeEncoder;

import java.util.Arrays;
import java.util.List;


/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 14:43
 * @email 226804871@qq.com
 */
public class ClientSupport extends BinaryClient implements JedisCommandsSupport {

    private Client client;

    public ClientSupport(Client client) {
        this.client = client;
    }

    /**
     * BloomFilter
     *
     * @param key
     */
    @Override
    public void bFadd(byte[] key,byte[] value) {
        ProtocolSupport.Command add = ProtocolSupport.Command.ADD;
        System.out.println(  new String(add.getRaw()));
        client.sendCommand(add,key,value);
    }

    @Override
    public void bFmadd(byte[] key, byte[]... values) {
        client.sendCommand(ProtocolSupport.Command.MADD,joinParameters(key,values));
    }

    @Override
    public void bFexists(byte[] key, byte[] values) {
        client.sendCommand(ProtocolSupport.Command.EXISTS,key,values);
    }


    @Override
    public void bFmexists(byte[] key,byte[][] values) {
        client.sendCommand(ProtocolSupport.Command.MEXISTS,joinParameters(key, values));
    }

    @Override
    public void bFreserve(byte[] key) {
        client.sendCommand(ProtocolSupport.Command.RESERVE,key);
    }

    @Override
    public void throttle(byte[] key, ThrottleParam throttleParam) {
        client.sendCommand(ProtocolSupport.Command.THROTTLE,key,SafeEncoder.encode(throttleParam.getCapacity()),SafeEncoder.encode(throttleParam.getOperations()),SafeEncoder.encode(throttleParam.getSeconds()),SafeEncoder.encode(throttleParam.getNeed()));
    }

    private byte[][] joinParameters(byte[] first, byte[][] rest) {
        byte[][] result = new byte[rest.length + 1][];
        result[0] = first;
        System.arraycopy(rest, 0, result, 1, rest.length);
        return result;
    }

    private byte[][] joinParameters(byte[] first, byte[] second, byte[][] rest) {
        byte[][] result = new byte[rest.length + 2][];
        result[0] = first;
        result[1] = second;
        System.arraycopy(rest, 0, result, 2, rest.length);
        return result;
    }


    @Override
    public String getBulkReply() {
       return client.getBulkReply();
    }

    @Override
    public byte[] getBinaryBulkReply() {
        return client.getBinaryBulkReply();
    }

    @Override
    public Long getIntegerReply() {
        return client.getIntegerReply();
    }

    @Override
    public List<String> getMultiBulkReply() {
        return client.getMultiBulkReply();
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<byte[]> getBinaryMultiBulkReply() {
        return client.getBinaryMultiBulkReply();
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Object> getRawObjectMultiBulkReply() {
        return client.getRawObjectMultiBulkReply();
    }
    @Override
    public List<Object> getObjectMultiBulkReply() {
        return  client.getObjectMultiBulkReply();
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Long> getIntegerMultiBulkReply() {
        return client.getIntegerMultiBulkReply();
    }
}
