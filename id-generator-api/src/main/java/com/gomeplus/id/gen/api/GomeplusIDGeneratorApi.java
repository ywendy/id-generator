package com.gomeplus.id.gen.api;

/**
 * 国美+ ID 生成器.
 *
 * @author Tony
 * @version 0.1.0
 */
public interface GomeplusIDGeneratorApi {


    /**
     * 获取业务类型下的唯一id
     * <pre>
     * business 取值范围:
     *     0.default  未知业务
     *     1.rebate 返利
     *     2.order 订单
     *     3.promotion 促销
     *     4.im 聊天
     *     5.item 商品
     *     6.account 账户
     *     7.settlement 结算
     *
     * Example:
     *  long result = instance.getId("rebate");
     * </pre>
     * <p/>
     * 如果想获取到包含业务类型的id，请传入上面的参数，如果获取到的参数不想包含业务id，请传入default
     * 或者null 或者 "",如果没有你想要的业务类型，请联系管理者添加.
     * 返回的id格式：
     * <p/>
     * <p/>
     * <pre>
     *
     * -------------------type=0------------------------------------
     *
     *  +-----------------------------------+
     *  |  41bit  |  5bit  |  8bit  | 10bit |
     *  +-----------------------------------+
     * timestamp(ms)+serverId+serviceId+sequence
     *
     * -------------------type=1,default------------------------------
     *
     *  +-----------------------------------+
     *  |  41bit  |  5bit  |  5bit  | 13bit |
     *  +-----------------------------------+
     * timestamp(ms)+serverId+serviceId+sequence
     *
     * -------------------type=2,im------------------------------------------------
     *
     *  +------------------------------------------------+
     *  |    41bit   |   13bit   |   5bit    |   5bit    |
     *  +------------------------------------------------+
     * timestamp(ms)+sequence+serverId+serviceId
     *
     *
     * -------------------type=3,im------------------------------------------------
     *
     *  +------------------------------------------------+
     *  |    31bit   |   5bit   |   5bit    |   23bit    |
     *  +------------------------------------------------+
     * timestamp(sec)+sequence+serverId+serviceId
     * </pre>
     *
     * @param business 上面的业务类型，如果没有会按照默认类型处理，也就是未知业务,不影响id的生成.
     * @param type     算法类型 参考上面呢的类型.
     * @return 根据时间计算出的id
     */
    public long getId(String business, int type);


    /**
     * <pre>
     *
     * long[] 仅支持type=2
     *
     * 如果num为0 则返回的数组里没有元素.
     *
     * </pre>
     *
     * @param business 业务类型.
     * @param num      数量，0<num<=2048
     * @return
     */
    public long[] getScopeId(String business, int type, int num);
}
