package com.gomeplus.id.gen;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.container.Container;
import com.gomeplus.id.gen.service.InitializationGenerator;
import com.gomeplus.id.gen.service.InitializationParameter;
import com.gomeplus.id.gen.service.holder.PropertiesHolder;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Tony
 * @version 0.1.0
 */
public class Bootstrap {
    public static final String CONTAINER_KEY = "dubbo.container";

    public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private static final ExtensionLoader<Container> loader = ExtensionLoader.getExtensionLoader(Container.class);

    private static volatile boolean running = true;

    public static void main(String[] args) {
        try {
            //Properties p = new Properties();
            // InputStream is =  Bootstrap.class.getResourceAsStream("/conf/dubbo.properties");
            // p.load(is);

            // System.out.println(">>>>>>"+p.get("dubbo.registry.address"));
         /*   ConfigUtils.setProperties(p);*/


            if (System.getProperty("dubbo.properties.file") == null) {
                Properties p = new Properties();
                InputStream is = Bootstrap.class.getResourceAsStream("/conf/dubbo.properties");
                p.load(is);

                System.out.println(">>>>>>" + p.get("dubbo.registry.address"));
                ConfigUtils.setProperties(p);
            }else {

                System.out.println(System.getProperty("dubbo.properties.file"));

                System.out.println(">>>>>" + ConfigUtils.getProperty("dubbo.registry.address"));
            }



            //init parameter
            long serverId = Long.parseLong(ConfigUtils.getProperty("server.id", "0"));

            InitializationGenerator initializationGenerator = new InitializationGenerator(serverId);


            String logFilePath = "logs";
            System.setProperty("logFilePath", ConfigUtils.getProperty("dubbo.log.file.path", "logs"));
            System.setProperty("logLlevel", ConfigUtils.getProperty("dubbo.log.level", "debug"));

            System.setProperty("logFileName", ConfigUtils.getProperty("dubbo.log.file.name",
                    "dubbo-id-generator-provider.log"));

            if (args == null || args.length == 0) {
                String config = ConfigUtils.getProperty(CONTAINER_KEY, loader.getDefaultExtensionName());
                args = Constants.COMMA_SPLIT_PATTERN.split(config);
            }

            final List<Container> containers = new ArrayList<Container>();
            for (int i = 0; i < args.length; i++) {
                containers.add(loader.getExtension(args[i]));
            }
            logger.info("Use container type(" + Arrays.toString(args) + ") to run dubbo serivce.");

            if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        for (Container container : containers) {
                            try {
                                container.stop();
                                logger.info("Dubbo " + container.getClass().getSimpleName() + " stopped!");
                            } catch (Throwable t) {
                                logger.error(t.getMessage(), t);
                            }
                            synchronized (Bootstrap.class) {
                                running = false;
                                Bootstrap.class.notify();
                            }
                        }
                    }
                });
            }

            for (Container container : containers) {
                container.start();
                logger.info("Dubbo " + container.getClass().getSimpleName() + " started!");
            }
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service " +
                    "server started!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            System.exit(1);
        }
        synchronized (Bootstrap.class) {
            while (running) {
                try {
                    Bootstrap.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }


}
