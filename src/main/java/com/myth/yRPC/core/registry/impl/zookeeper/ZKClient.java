package com.myth.yRPC.core.registry.impl.zookeeper;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.List;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-07 16:54
 **/
public class ZKClient {
    public static Logger logger = LoggerFactory.getLogger(ZKClient.class);
    private static CuratorFramework client;
    public static CuratorFramework getClient(){
        if (client == null) {
            synchronized (ZKClient.class) {
                if (client == null) {
                    try {
                        Configuration config = new Configurations().properties(new File("zk.properties"));
                        String hosts = config.getString("zk.hosts");
                        if (hosts == null) {
                            throw new RuntimeException("Need right conf for zookeeper hosts.");
                        }
                        String namespace = config.getString("zk.root");
                        logger.info("zkHosts: {}", hosts);
                        client = CuratorFrameworkFactory
                                .builder()
                                .connectString(hosts)
                                .namespace(namespace)
                                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 5 * 60 * 1000))
                                .connectionTimeoutMs(5000).build();

                    } catch (Exception e) {
                        logger.error("get client error", e);
                    }
                    client.start();
                }
            }
        }
        return client;
    }
    public static void destory() {
        client.close();
    }

    public static void main(String[] args) {
        try {
            getClient();
            List<String> ret = getClient().getChildren().forPath("/");
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
