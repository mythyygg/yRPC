package com.myth.yRPC.core.registry;

import java.util.List;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-07 16:27
 **/
public class Service {
    String serviceId;
    List<Node> nodes;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
