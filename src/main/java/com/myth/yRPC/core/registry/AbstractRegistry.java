package com.myth.yRPC.core.registry;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: IRegistry 的一个基础实现类
 * @author: yuang gang
 * @create: 2019-03-07 15:46
 **/
public abstract class AbstractRegistry implements IRegistry {

    private Map<String, LocalConfig> hostConfigCache = new HashMap<>();
    private Map<String, Service> serviceMap = new ConcurrentHashMap<>();
    @Override
    public List<Node> queryService(String serviceId) {
        List<Node> nodes = getLocalConfigCache(serviceId);
        if (nodes != null) {
            return nodes;
        }
        Service service = getService(serviceId);
        if (service == null) {
            return null;
        }
        return service.getNodes();
    }


    /** 从本地缓存中获取服务节点 */
    public List<Node> getLocalConfigCache(String serviceId) {
        LocalConfig localConfig = hostConfigCache.get(serviceId);
        long curTime = new Date().getTime();
        if (localConfig != null && curTime - localConfig.getTime() < 10000) {
            return localConfig.getNodes();
        }
        return null;
    }

    /*todo*/
    public List<Node> getLocalConfigFromSysProp(String serviceId) {
        return null;
    }

    private Service getService(String serviceId) {
        Service service = serviceMap.get(serviceId);
        if (service == null) {
            synchronized (serviceId.intern()) {
                service = serviceMap.get(serviceId);
                if (service == null) {
                    service = loadService(serviceId);
                    if (service != null) {
                        serviceMap.put(serviceId, service);
                    }
                }
            }
        }
        return service;
    }

    protected abstract Service loadService(String serviceId);
}
