package com.myth.yRPC.core.registry.impl.zookeeper;

public class ZKPathUtil {
    public static final String ENABLE = "enable";
    public static final String DISABLE = "disable";

    /**
     * 给定serviceId，计算出其对应的path，相对路径
     * 可用节点
     *
     * @param serviceId
     * @return
     */
    public static String serviceIdToEnablePath(String serviceId, String identify) {
        StringBuilder sb = new StringBuilder();
        return sb.append("/").append(serviceId).append("/").append(ENABLE).append("/").append(identify).toString();
    }

    public static String serviceIdToEnablePath(String serviceId) {
        StringBuilder sb = new StringBuilder();
        return sb.append("/").append(serviceId).append("/").append(ENABLE).toString();
    }

    /**
     * 给定serviceId，计算出其对应的path，相对路径
     * 不可用节点
     *
     * @param serviceId
     * @return
     */
    public static String serviceIdToDisablePath(String serviceId, String identify) {
        StringBuilder sb = new StringBuilder();
        return sb.append("/").append(serviceId).append("/").append(DISABLE).append("/").append(identify).toString();
    }

    public static String serviceIdToDisablePath(String serviceId) {
        StringBuilder sb = new StringBuilder();
        return sb.append("/").append(serviceId).append("/").append(DISABLE).toString();
    }
}