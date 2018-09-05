package redis.clients.jedis.plugin.params;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/30 17:46
 * @email 226804871@qq.com
 * > cl.throttle laoqian:reply 15 30 60 1
 *                       ▲   ▲  ▲ ▲ ▲
 *                       |     |  |  |  └───── need 1 quota (可选参数，默认值也是1)
 *                       |     |  └──┴─────── 30 operations / 60 seconds 这是漏水速率
 *                       |     └───────────── 15 capacity 这是漏斗容量
 *                       └─────────────────── key laoqian...
 *
 * 上面这个指令的意思是允许「用户老钱回复行为」的频率为每 60s 最多 30 次(漏水速率)，漏斗的初始容量为 15，也就是说一开始可以连续回复 15 个帖子，然后才开始受漏水速率的影响。我们看到这个指令中漏水速率变成了 2 个参数，替代了之前的单个浮点数。用两个参数相除的结果来表达漏水速率相对单个浮点数要更加直观一些。
 * <p>
 * > cl.throttle laoqian:reply 15 30 60
 * 1) (integer) 0   # 0 表示允许，1表示拒绝
 * 2) (integer) 15  # 漏斗容量capacity
 * 3) (integer) 14  # 漏斗剩余空间left_quota
 * 4) (integer) -1  # 如果拒绝了，需要多长时间后再试(漏斗有空间了，单位秒)
 * 5) (integer) 2   # 多长时间后，漏斗完全空出来(left_quota==capacity，单位秒)...
 */
public class ThrottleParam {

    private Integer capacity = 15;
    private Integer operations = 30;
    private Integer seconds  = 60;
    private Integer need = 1;



    public ThrottleParam() {

    }

    public ThrottleParam(Integer capacity, Integer operations, Integer seconds) {
        this.capacity = capacity;
        this.operations = operations;
        this.seconds = seconds;
    }

    public ThrottleParam(Integer capacity, Integer operations, Integer seconds, Integer need) {
        this.capacity = capacity;
        this.operations = operations;
        this.seconds = seconds;
        this.need = need;
    }

    public String getCapacity() {
        return capacity+"";
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getOperations() {
        return operations+"";
    }

    public void setOperations(Integer operations) {
        this.operations = operations;
    }

    public String getSeconds() {
        return seconds+"";
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getNeed() {
        return need+"";
    }

    public void setNeed(Integer need) {
        this.need = need;
    }
}
