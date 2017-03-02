<strong>snowflake ID生成算法</strong>

符号位|时间戳|数据中心Id|工作机器ID|sequence
---|---|---|---|---
1|41|5|5|12
符号位不使用|时间戳（ms）((1L<<41)-1)/(3600*24*365*1000l) = 69 年|dataCenterId|workerId(1<<10)-1 = 1023 表示1023 个数|序列号sequence (1<<12)-1 = 4095 毫秒内自增数量

<strong>具体算法</strong>

    1s 内单台机器可以表示 4095*1000 = 4095000 个自增的数
    ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence;
    (timestamp - twepoch) << timestampLeftShift) 在后面补0 41位时间戳后面需要22（5+5+12）个0
    (dataCenterId << dataCenterIdShift)  dataCenterId 占5位，需要对齐上面的41位后的第一个0
    (timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) 把41位后的5位置为dataCenterId
    后面同理
    此行返回一个64位的整数

    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    获取sequenceMask = 111111111111; 12位1 用来做& 操作
    sequence = (sequence + 1) & sequenceMask;
    sequence 和 sequenceMask 做& 操作，是在前面补0到12位





-------------------------------------------------------------------------------------

<strong>ID gomeplus</storng>




时间戳|工作机器ID|业务线ID|sequence
---|---|---|---
41|5|8|10
timstamp|serverId|businessId|sequence





    使用 <code>id-generator-benchmark/GomeplusIDGeneratorBenchmark</code> 提供benchmark测试
    TODO dubbo benchmark

提供Dubbo服务

    clean install -Dmaven.test.skip=true
    copy id-generator-provider...tar.gz
    windows
    解压tar.gz 文件，invoke bin/start.bat
    linux
    tar -zxvf XXX.tar.gz
    invoke bin/start.sh

    the port is 20880 and the zookeeper address is 10.125.31.7:2181
    具体参考resources/dubbo.properties

    启动dubbo服务后，打开控制台
    telnet 127.0.0.1 20880
    ls
    ls -l com.gomeplus.id.gen.service.GomeplusIDGeneratorService
    invoke com.gomeplus.id.gen.service.GomeplusIDGeneratorService.getId("rebate")

    参考地址：http://alibaba.github.io/dubbo-doc-static/Telnet+Command+Reference-zh-showComments=true&showCommentArea=true.htm

HTTP 服务

    maven clean install -Dmaven.test.skip=true

    copy id-generator-web-1.0-SNAPSHOT-assembly.tar.gz
    windows
    解压文件，execute bin/start.bat
    linux
    tar -zxvf XXXx.tar.gz
    cd bin
    execute bin/start.sh

    jetty port config by conf.properties


NOTE:
    1、部署服务需要确认服务器的时间不能倒退
    2、部署到不同服务时，请设置不同的serverId
    3、

















