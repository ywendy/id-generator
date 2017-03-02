package com.gomeplus.id.gen.service.filter;

import com.alibaba.dubbo.rpc.*;
import com.gomeplus.id.gen.common.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * record log session id.
 *
 * @author Tony
 * @version 0.1.0
 */
public class LogSessionFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {


        String logSessionId = RpcContext.getContext().getAttachment(Constants.LOG_SESSION_ID);
        if (StringUtils.isBlank(logSessionId)) {
            logSessionId = MDC.get(Constants.LOG_SESSION_ID);
            if (StringUtils.isBlank(logSessionId)) {
                logSessionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            }

        }
        RpcContext.getContext().setAttachment(Constants.LOG_SESSION_ID, logSessionId);
        MDC.put(Constants.LOG_SESSION_ID,logSessionId);
        return invoker.invoke(invocation);
    }
}
