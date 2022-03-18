package org.cloud.sonic.agent.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ZhouYiXun
 * @des Agent管理者
 * @date 2021/8/26 22:23
 */
@Component
public class AgentManagerTool {
    private final static Logger logger = LoggerFactory.getLogger(AgentManagerTool.class);

    private static ConfigurableApplicationContext context;

    @Autowired
    public void setContext(ConfigurableApplicationContext c) {
        AgentManagerTool.context = c;
    }

    public static void stop() {
        context.close();
        logger.info("Bye！");
    }

    public static void update(String version) {
        logger.info("Updating...");
        try {
            DownloadTool.update(version);
        } catch (IOException e) {
            logger.info("Update agent version failed!");
            logger.error(e.getMessage());
        } finally {
            logger.info("Done.");
        }
    }
}
