package com.myth.yRPC.core.registry;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-07 15:29
 **/
public class Node {
    private String host;
    private int port;

    public Node(String host, int port) {
        this.host = host;
        this.port = port;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node otherNode = (Node) obj;
        if (StringUtils.equals(host, otherNode.getHost()) &&
                port == otherNode.getPort()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (host+":"+port).hashCode();
    }

    public String getIdentity() {
        return host + ":" + port;
    }

    public static Node getNodeFromIdentiry(String identiry) {
        try {
            String[] conts = StringUtils.split(identiry, ":");
            String host = conts[0];
            String port = conts[1];
            return new Node(host, Integer.valueOf(port));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Node> getNodeListFromIdentity(String identity) {
        List<Node> nodes = new ArrayList<>();
        String[] identityList = StringUtils.split(identity, ",");
        for (String item : identityList) {
            Node node = getNodeFromIdentiry(item);
            if (node != null) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
