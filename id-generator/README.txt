snowflake

------------------------------------------------
|   1   |   41    |    5    |    5    |   12  |
------------------------------------------------

64位



1:符号位不使用
41：时间戳（ms）((1L<<41)-1)/(3600*24*365*1000l) = 69 年
5： dataCenterId
5:  workerId
  (1<<10)-1 = 1023 表示1023 个数
12：序列号sequence (1<<12)-1 = 4095 毫秒内自增数量

1s 内单台机器可以表示 4095*1000 = 4095000 个自增的数

((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence;

(timestamp - twepoch) << timestampLeftShift) 在后面补0 41位时间戳后面需要22（5+5+12）个0
(dataCenterId << dataCenterIdShift)  dataCenterId 占5位，需要对齐上面的41位后的第一个0
(timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) 把41位后的5位置为dataCenterId
后面同理（ | 或操作，相同位假（0），不同位真（1））
此行返回一个64位的整数


 private long sequenceMask = -1L ^ (-1L << sequenceBits);

 获取sequenceMask = 111111111111; 12位1 用来做& 操作
   sequence = (sequence + 1) & sequenceMask;
   & 相同位真，不同位假（0）
   sequence 和 sequenceMask 做& 操作，是在前面补0到12位
   (如果想让一个数有N位，可以做& N个1位操作)




-------------------------------------------------------------------------------------

ID gomeplus

-------------------------
|  41  |  5  |  8  | 10 |
-------------------------

time+serverId+serviceId+seq






















