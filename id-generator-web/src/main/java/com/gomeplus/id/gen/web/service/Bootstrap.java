package com.gomeplus.id.gen.web.service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.gomeplus.id.gen.web.holder.PropertiesHolder;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tony
 * @version 0.1.0
 */
public class Bootstrap {

    public static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    private   Server server;


    public void start(){

        try {
            initWebService();
        } catch (Exception e) {
            LOGGER.error("init web service error ! exit -1",e);
            System.exit(-1);
        }


    }

    public void stop(){
        if (server !=null){
            try {
                server.stop();
            } catch (Exception e) {
                LOGGER.error("stop the server error !",e);
            }
        }
    }

    private void initWebService() throws Exception {

        Configuration config = new PropertiesConfiguration("conf/conf.properties");
        PropertiesHolder.initProperties(config);


        int port = config.getInt("jetty.port");
        server = new Server(port);
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(this.getClass().getClassLoader().getResource("conf/logback.xml"));
        } catch (JoranException e) {
            e.printStackTrace();
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath(config.getString("server.context.path","/"));
        server.setHandler(servletContextHandler);
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "com.gomeplus.id.gen.web");

        servletContextHandler.addServlet(sh, "/*");

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                LOGGER.info("the service stop ok!");
            }
        });

        server.start();


    }


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
    }


}
